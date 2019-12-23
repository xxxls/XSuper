package com.xxxxls.adapter.paging.page_keyed

import androidx.paging.PageKeyedDataSource
import com.xxxxls.adapter.paging.LoadErrorCallback
import com.xxxxls.adapter.paging.OnListStatusListener
import com.xxxxls.adapter.paging.XSuperListStatus

/**
 * PageKeyedFrontLoadCallback
 * @author Max
 * @date 2019-12-20.
 */
class PageKeyedFrontLoadCallback<Key, Value>(
    val callback: PageKeyedDataSource.LoadCallback<Key, Value>,
    val status: OnListStatusListener?,
    private val retry: (() -> Unit)? = null
) :
    PageKeyedDataSource.LoadCallback<Key, Value>(), LoadErrorCallback {

    override fun onResult(data: List<Value>, adjacentPageKey: Key?) {
        callback.onResult(data, adjacentPageKey)
        status?.onListStatusChange(XSuperListStatus.FrontLoadMoreIn)
    }


    override fun onError(throwable: Throwable?) {
        status?.onListStatusChange(XSuperListStatus.FrontLoadMoreError(retry))
    }

}