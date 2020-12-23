package com.xxxxls.adapter.paging.item_keyed

import androidx.paging.ItemKeyedDataSource
import com.xxxxls.adapter.paging.LoadErrorCallback
import com.xxxxls.adapter.paging.OnListStatusListener
import com.xxxxls.adapter.paging.XSuperListStatus

/**
 * ItemKeyedLoadCallback
 * @author Max
 * @date 2019-12-20.
 */
class ItemKeyedLoadCallback<Value>(
    val callback: ItemKeyedDataSource.LoadCallback<Value>,
    val status: OnListStatusListener?,
    private val retry: (() -> Unit)? = null
) :
    ItemKeyedDataSource.LoadCallback<Value>(), LoadErrorCallback {

    override fun onResult(data: List<Value>) {
        callback.onResult(data)
        status?.onListStatusChange(XSuperListStatus.LoadMoreSuccess())
    }

    override fun onError(throwable: Throwable?) {
        status?.onListStatusChange(XSuperListStatus.LoadMoreError(retry))
    }

}