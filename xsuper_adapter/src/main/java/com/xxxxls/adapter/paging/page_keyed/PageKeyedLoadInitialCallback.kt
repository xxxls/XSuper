package com.xxxxls.adapter.paging.page_keyed

import androidx.paging.PageKeyedDataSource
import com.xxxxls.adapter.paging.LoadErrorCallback
import com.xxxxls.adapter.paging.OnListStatusListener
import com.xxxxls.adapter.paging.XSuperListStatus

/**
 * PageKeyedLoadInitialCallback
 * @author Max
 * @date 2019-12-20.
 */
class PageKeyedLoadInitialCallback<Key,Value>(
    val callback: PageKeyedDataSource.LoadInitialCallback<Key,Value>,
    val status: OnListStatusListener?,
    private val retry: (() -> Unit)? = null
) :
    PageKeyedDataSource.LoadInitialCallback<Key,Value>(), LoadErrorCallback {
    override fun onResult(
        data: List<Value>,
        position: Int,
        totalCount: Int,
        previousPageKey: Key?,
        nextPageKey: Key?
    ) {
        callback.onResult(data, position, totalCount, previousPageKey, nextPageKey)
        status?.onListStatusChange(XSuperListStatus.InitializeSuccess())
    }

    override fun onResult(data: List<Value>, previousPageKey: Key?, nextPageKey: Key?) {
        callback.onResult(data, previousPageKey, nextPageKey)
        status?.onListStatusChange(XSuperListStatus.InitializeSuccess())
    }

    override fun onError(throwable: Throwable?) {
        status?.onListStatusChange(XSuperListStatus.InitializeError(retry))
    }

}