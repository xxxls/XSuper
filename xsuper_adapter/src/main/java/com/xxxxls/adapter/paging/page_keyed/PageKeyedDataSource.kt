package com.xxxxls.adapter.paging.page_keyed

import androidx.paging.PageKeyedDataSource
import com.xxxxls.adapter.paging.OnListStatusListener
import com.xxxxls.adapter.paging.XSuperListStatus

/**
 * PageKeyedDataSource
 * @author Max
 * @date 2019-12-19.
 */
class PageKeyedDataSource<Key, Value>(
    val dataSource: IPageKeyedDataSource<Key, Value>,
    val statusListener: OnListStatusListener?
) : PageKeyedDataSource<Key, Value>() {

    //重试操作
    var retry: (() -> Unit)? = null

    override fun loadInitial(
        params: LoadInitialParams<Key>,
        callback: LoadInitialCallback<Key, Value>
    ) {
        statusListener?.onListStatusChange(XSuperListStatus.Initialize)
        retry = {
            loadInitial(params, callback)
        }

        dataSource.loadInitial(
            params,
            PageKeyedLoadInitialCallback(
                callback = callback,
                status = statusListener,
                retry = retry
            )
        )
    }

    override fun loadAfter(params: LoadParams<Key>, callback: LoadCallback<Key, Value>) {
        statusListener?.onListStatusChange(XSuperListStatus.LoadMoreIn)
        retry = {
            loadAfter(params, callback)
        }
        dataSource.loadAfter(
            params,
            PageKeyedLoadCallback(
                callback = callback,
                status = statusListener,
                retry = retry
            )
        )
    }

    override fun loadBefore(params: LoadParams<Key>, callback: LoadCallback<Key, Value>) {
        statusListener?.onListStatusChange(XSuperListStatus.FrontLoadMoreIn)
        retry = {
            loadBefore(params, callback)
        }
        dataSource.loadBefore(
            params,
            PageKeyedFrontLoadCallback(callback = callback, status = statusListener, retry = retry)
        )
    }

}