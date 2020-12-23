package com.xxxxls.adapter.paging.item_keyed

import androidx.paging.ItemKeyedDataSource
import com.xxxxls.adapter.paging.LoadErrorCallback
import com.xxxxls.adapter.paging.OnListStatusListener
import com.xxxxls.adapter.paging.XSuperListStatus

/**
 * ItemKeyedFrontLoadCallback
 * @author Max
 * @date 2019-12-20.
 */
class ItemKeyedFrontLoadCallback<Value>(
    val callback: ItemKeyedDataSource.LoadCallback<Value>,
    val status: OnListStatusListener?,
    private val retry: (() -> Unit)? = null
) :
    ItemKeyedDataSource.LoadCallback<Value>(), LoadErrorCallback {

    override fun onResult(data: List<Value>) {
        callback.onResult(data)
        status?.onListStatusChange(XSuperListStatus.FrontLoadMoreSuccess())
    }

    override fun onError(throwable: Throwable?) {
        status?.onListStatusChange(XSuperListStatus.FrontLoadMoreError(retry))
    }

}