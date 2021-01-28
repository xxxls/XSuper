package com.xxxxls.module_base.refresh

import android.content.Context
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import androidx.recyclerview.widget.RecyclerView
import com.xxxxls.module_base.adapter.IListAdapter
import com.xxxxls.module_base.mvvm.BaseLiveData
import com.xxxxls.module_base.network.response.IListResponse
import com.xxxxls.module_base.util.ILog
import com.xxxxls.module_base.util.showToast
import com.xxxxls.module_base.widget.refresh.IRefreshLayout
import com.xxxxls.module_base.widget.refresh.OnRefreshLoadMoreListener
import com.xxxxls.status.IStatusView
import com.xxxxls.status.Status
import com.xxxxls.status.getStatusView
import com.xxxxls.status.showLoading
import com.xxxxls.xsuper.callback.SuperCallBack
import java.util.LinkedHashSet

/**
 * 列表刷新帮助类
 * 可快速实现一个包含`分页加载`，`多状态`的功能列表。
 * @author Max
 * @date 2019-12-19.
 */
open class SmartListRefreshHelper<T>(
    /*生命周期感应*/
    private val lifecycleOwner: LifecycleOwner,

    /*视图层*/
    private val view: IView<T>,

    /*数据层*/
    private val source: ISource<T>,

    /*配置*/
    private val config: Config
) : ILog, LifecycleObserver {

    /**
     * 当前页码
     */
    private var page: Int = config.initPageIndex

    /**
     * 是否刷新
     */
    private var isRefresh = true

    /**
     * 刷新监听器
     */
    private var refreshListeners: LinkedHashSet<OnRefreshLoadMoreListener>? = null

    /**
     * 数据加载成功-拦截器
     * @param Boolean 是否刷新操作
     * @param IListResponse 数据响应体
     */
    private var dataSuccessInterceptors: (LinkedHashSet<((Boolean, IListResponse<T>) -> Boolean)>)? =
        null

    /**
     * 数据加载失败-拦截器
     * @param Boolean 是否刷新操作
     * @param Throwable 异常信息
     */
    private var dataFailedInterceptors: (LinkedHashSet<((Boolean, Throwable) -> Boolean)>)? = null

    // adapter 数据监听
    private var adapterDataObserver: RecyclerView.AdapterDataObserver? = null

    /**
     * listData
     */
    private val listData: BaseLiveData<IListResponse<T>> by lazy {
        BaseLiveData<IListResponse<T>>(false).apply {
            observe(lifecycleOwner,
                success = {
                    page++
                    val isInterceptor = dataSuccessInterceptors?.any { interpolator ->
                        interpolator.invoke(isRefresh, it)
                    } ?: false
                    //有拦截器拦截，则不默认处理
                    if (!isInterceptor) {
                        loadDataSuccess(isRefresh, it)
                    }
                }, failure = {
                    val isInterceptor = dataFailedInterceptors?.any { interpolator ->
                        interpolator.invoke(isRefresh, it)
                    } ?: false
                    //有拦截器拦截，则不默认处理
                    if (!isInterceptor) {
                        loadDataFail(isRefresh, it)
                    }
                })
        }
    }

    init {
        lifecycleOwner.lifecycle.addObserver(this)

        view.getRefreshLayout().setOnRefreshLoadMoreListener(onRefresh = {
            refresh()
        }, onLoadMore = {
            loadMore()
        })
    }

    /**
     * 刷新并展示loading状态
     * 注意：若未启动多状态，并不会触发切换loading
     * @param delayMillis 延时展示状态
     */
    fun refreshAndShowLoading(delayMillis: Long = 0L) {
        getStatusView().showLoading(delayMillis)
        refresh()
    }

    /**
     * 刷新
     */
    fun refresh() {
        page = config.initPageIndex
        refreshListeners?.forEach {
            it.onRefresh()
        }
        request(true)
    }

    /**
     * 加载更多
     */
    fun loadMore() {
        refreshListeners?.forEach {
            it.onLoadMore()
        }
        request(false)
    }

    /**
     * 请求数据
     */
    private fun request(isRefresh: Boolean) {
        log("request() isRefresh:$isRefresh page:$page")
        this.isRefresh = isRefresh
        source.requestList(page, listData)
    }

    /**
     * 启用多状态视图功能(不可多次调用此方法)
     * 注意：只调用此方法是无法启用多状态功能的，还需要配置好状态视图组件
     */
    fun enableStatus() {
        getAdapter().getRecyclerViewAdapter()
            .registerAdapterDataObserver(getAdapterDataObserver())

        addDataInterceptorForSuccess { isRefresh, data ->
            if (getStatusView()?.getStatus() != Status.Content) {
                //展示内容页面
                getStatusView()?.switchStatus(Status.Content)
            }
            false
        }

        addDataInterceptorForFailed { isRefresh, throwable ->
            if (isRefresh) {
                //刷新失败时，展示错误页面
                getStatusView()?.switchStatus(Status.Error)
            }
            false
        }
    }

    /**
     * 启用toast提示功能(不可多次调用此方法)
     */
    fun enableToast(context: Context? = null) {
        addDataInterceptorForFailed { isRefresh, throwable ->
            // 失败后toast提示
            throwable.showToast(context)
            false
        }
    }

    /**
     * 加载成功
     */
    private fun loadDataSuccess(isRefresh: Boolean, data: IListResponse<T>) {
        log("loadDataSuccess() isRefresh:$isRefresh ,hasNextPage:${data.isHasNextPage()}")
        when {
            isRefresh -> {
                //当前刷新状态
                getAdapter().replaceData(data.getListNoNull())
                getRefreshLayout().finishRefresh(success = true, noMoreData = !data.isHasNextPage())
            }
            else -> {
                //加载更多状态
                getAdapter().addData(data.getListNoNull())
                getRefreshLayout().finishLoadMore(
                    success = true,
                    noMoreData = !data.isHasNextPage()
                )
            }
        }
        getRefreshLayout().setNoMoreData(!data.isHasNextPage())
    }

    /**
     * 加载失败
     */
    private fun loadDataFail(isRefresh: Boolean, throwable: Throwable) {
        log("loadDataFail() isRefresh:$isRefresh")
        when {
            isRefresh -> {
                //当前刷新状态
                getRefreshLayout().finishRefresh(success = false, noMoreData = false)
            }
            else -> {
                //加载更多状态
                getRefreshLayout().finishLoadMore(success = false, noMoreData = false)
            }
        }
    }

    /**
     * 设置预加载
     * @param preLoadNumber 滑动到倒数第几个时,就开始加载下一页数据。(当该数 大于0 时才有效)
     */
    fun setPreLoad(preLoadNumber: Int) {
        //TODO 待实现
//        getAdapter().setPreLoadNumber(preLoadNumber) {
//
//        }
    }

    /**
     * 获取状态视图
     */
    private fun getStatusView(): IStatusView? {
        return view.getStatusView()
    }

    /**
     * 获取刷新视图
     */
    private fun getRefreshLayout(): IRefreshLayout {
        return view.getRefreshLayout()
    }

    /**
     * 获取刷新视图View
     */
    private fun getRefreshLayoutView(): View? {
        return view.getRefreshLayout() as? View
    }

    /**
     * 获取adapter
     */
    private fun getAdapter(): IListAdapter<T> {
        return view.getAdapter()
    }

    /**
     * 添加数据加载成功的拦截器
     */
    fun addDataInterceptorForSuccess(interceptor: (Boolean, IListResponse<T>) -> Boolean) {
        if (dataSuccessInterceptors == null) {
            this.dataSuccessInterceptors = LinkedHashSet()
        }
        this.dataSuccessInterceptors!!.add(interceptor)
    }

    /**
     * 添加数据加载失败的拦截器
     */
    fun addDataInterceptorForFailed(interceptor: (Boolean, Throwable) -> Boolean) {
        if (dataFailedInterceptors == null) {
            this.dataFailedInterceptors = LinkedHashSet()
        }
        this.dataFailedInterceptors!!.add(interceptor)
    }

    // adapter 数据监听
    private fun getAdapterDataObserver(): RecyclerView.AdapterDataObserver {
        if (adapterDataObserver == null) {
            adapterDataObserver = object : RecyclerView.AdapterDataObserver() {
                override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
                    super.onItemRangeRemoved(positionStart, itemCount)
                    if (getAdapter().getItemCount() == 0 && getStatusView()?.getStatus() != Status.Empty) {
                        getStatusView()?.switchStatus(Status.Empty)
                    }
                }

                override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
                    super.onItemRangeInserted(positionStart, itemCount)
                    if (getAdapter().getItemCount() > 0 && getStatusView()?.getStatus() != Status.Content) {
                        getStatusView()?.switchStatus(Status.Content)
                    }
                }

                override fun onChanged() {
                    super.onChanged()
                    if (getAdapter().getItemCount() > 0) {
                        if (getStatusView()?.getStatus() != Status.Content) {
                            getStatusView()?.switchStatus(Status.Content)
                        }
                    } else {
                        if (getStatusView()?.getStatus() != Status.Empty) {
                            getStatusView()?.switchStatus(Status.Empty)
                        }
                    }
                }
            }
        }
        return adapterDataObserver!!
    }

    private fun log(message: String) {
        logD(message)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        adapterDataObserver?.let {
            getAdapter().getRecyclerViewAdapter().unregisterAdapterDataObserver(it)
            adapterDataObserver = null
        }
        refreshListeners?.clear()
        refreshListeners = null
        dataSuccessInterceptors?.clear()
        dataSuccessInterceptors = null
        dataFailedInterceptors?.clear()
        dataFailedInterceptors = null
    }

    /**
     * 配置
     */
    data class Config(
        /*初始时加载的页码索引*/
        val initPageIndex: Int,
        /*预加载：滑动到倒数第几个时,就开始加载下一页数据。(当该数 大于0 时才有效)*/
        val preLoadNumber: Int?
    )

    /**
     * 视图
     */
    interface IView<T> {

        /**
         * 列表适配器
         */
        fun getAdapter(): IListAdapter<T>

        /**
         * 获取刷新器
         */
        fun getRefreshLayout(): IRefreshLayout

        /**
         * 获取状态视图
         */
        fun getStatusView(): IStatusView? {
            // 默认是情况下，刷新容器即为状态视图
            return (getRefreshLayout() as? View)?.getStatusView()
        }
    }

    /**
     * 数据源
     */
    interface ISource<T> {

        /**
         * 请求列表数据
         * @param page 页码
         * @param callBack 结果回调
         */
        fun requestList(page: Int, callBack: SuperCallBack<IListResponse<T>>)
    }

}