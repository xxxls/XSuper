package com.xxxxls.adapter.paging.item_keyed

import androidx.paging.ItemKeyedDataSource
import com.xxxxls.adapter.paging.OnListStatusListener
import com.xxxxls.adapter.paging.XSuperListStatus

/**
 * ItemKeyedDataSource
 * @author Max
 * @date 2019-12-17.
 */
class ItemKeyedDataSource<Key, Value>(
    val dataSource: IItemKeyedDataSource<Key, Value>,
    val statusListener: OnListStatusListener?
) :
    ItemKeyedDataSource<Key, Value>() {

    //重试操作
    var retry: (() -> Unit)? = null

    override fun loadInitial(params: LoadInitialParams<Key>, callback: LoadInitialCallback<Value>) {
        statusListener?.onListStatusChange(XSuperListStatus.Initialize)
        retry = {
            loadInitial(params, callback)
        }

        dataSource.loadInitial(
            params,
            ItemKeyedLoadInitialCallback(
                callback = callback,
                status = statusListener,
                retry = retry
            )
        )
    }

    override fun loadAfter(params: LoadParams<Key>, callback: LoadCallback<Value>) {
        statusListener?.onListStatusChange(XSuperListStatus.LoadMoreIn)
        retry = {
            loadAfter(params, callback)
        }
        dataSource.loadAfter(
            params,
            ItemKeyedLoadCallback(callback = callback, status = statusListener, retry = retry)
        )
    }

    override fun loadBefore(params: LoadParams<Key>, callback: LoadCallback<Value>) {
        statusListener?.onListStatusChange(XSuperListStatus.FrontLoadMoreIn)
        retry = {
            loadAfter(params, callback)
        }

        dataSource.loadBefore(
            params,
            ItemKeyedFrontLoadCallback(callback = callback, status = statusListener, retry = retry)
        )
    }

    override fun getKey(item: Value): Key {
        return dataSource.getKey(item)
    }

}
