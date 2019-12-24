package com.xxxxls.adapter.paging

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.xxxxls.utils.L

/**
 * Paging
 * @author Max
 * @date 2019-12-19.
 */
class XSuperPaging<Key, Value>(
    val dataSourceFactory: XSuperDataSourceFactory<Key, Value>,
    val config: PagedList.Config,
    val statusLiveData: MutableLiveData<XSuperListStatus>? = null,
    val builder: ((livePagedListBuilder: LivePagedListBuilder<Key, Value>) -> Unit)? = null
) : OnListStatusListener {

    //当前状态
    var status: XSuperListStatus? = null

    /**
     * 刷新
     */
    fun refresh(): Boolean {
        dataSourceFactory.dataSourceLiveData.value?.invalidate()
        return true
    }

    /**
     * 重试
     * @return 是否成功调用重试操作
     */
    fun retry(): Boolean {
        L.e("XSuperPaging -> retry()  status:$status")

        if (status?.isError() == false) {
            //失败类型才重试
            L.e("XSuperPaging -> isError")
            return false
        }

        if (status?.retry == null) {
            //无重试操作
            L.e("XSuperPaging -> retry == null")
            return false
        }

        status?.retry?.invoke()
        return true
    }

    fun build(): LiveData<PagedList<Value>> {
        dataSourceFactory.statusListener = this
        return LivePagedListBuilder(
            dataSourceFactory,
            config
        ).apply {
            //边界回调
            setBoundaryCallback(XSuperBoundaryCallback<Value>(this@XSuperPaging))
            builder?.invoke(this)
        }.build()
    }

    override fun onListStatusChange(status: XSuperListStatus) {
        L.e("XSuperPaging -> $status")
        this.status = status
        statusLiveData?.postValue(status)
    }

}