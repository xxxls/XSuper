package com.xxxxls.module_base.widget.refresh

import androidx.recyclerview.widget.RecyclerView

/**
 * 刷新列表组件
 * @author Max
 * @date 1/28/21.
 */
interface IRefreshLayout {

    /**
     * 是否正在刷新
     */
    fun isRefreshing(): Boolean

    /**
     * 是否正在加载
     */
    fun isLoading(): Boolean

    /**
     * 显示刷新动画并且触发刷新事件
     * @param delayed 开始延时
     * @return true or false, 是否成功（状态不符合会失败）
     */
    fun autoRefresh(delayed: Int? = null): Boolean

    /**
     * 显示加载动画并且触发刷新事件
     * @param delayed 开始延时
     * @return true or false, 是否成功（状态不符合会失败）
     */
    fun autoLoadMore(delayed: Int? = null): Boolean

    /**
     * 获取RecyclerView
     */
    fun getRecyclerView(): RecyclerView?

    /**
     * 结束刷新
     * @param delayed 开始延时
     * @param success 数据是否成功刷新
     * @param noMoreData 是否有更多数据
     */
    fun finishRefresh(success: Boolean = true, noMoreData: Boolean = true, delayed: Int? = null)

    /**
     * 结束加载更多
     * @param delayed 开始延时
     * @param success 数据是否成功
     * @param noMoreData 是否有更多数据
     */
    fun finishLoadMore(success: Boolean = true, noMoreData: Boolean = true, delayed: Int? = null)

    /**
     * 设置没有更多数据了
     * @param noMoreData 是否有更多数据
     * @param tag 这个字段没有意义，只是为了与SmartRefreshLayout的方法做出区分
     */
    fun setNoMoreData(noMoreData: Boolean = true, tag: Any? = null)

    /**
     * 是否启用下拉刷新（默认启用）
     */
    fun setEnableRefresh(enabled: Boolean = true, tag: Any? = null)

    /**
     * 设置是否启用上拉加载更多
     */
    fun setEnableLoadMore(enabled: Boolean = true, tag: Any? = null)

    /**
     * 设置刷新与加载更多监听
     * @param onRefresh 刷新事件
     * @param onLoadMore 加载更多事件
     */
    fun setOnRefreshLoadMoreListener(
        onRefresh: (IRefreshLayout) -> Unit,
        onLoadMore: (IRefreshLayout) -> Unit
    )
}