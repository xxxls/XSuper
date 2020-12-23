package com.xxxxls.adapter.paging.item_keyed

import androidx.paging.ItemKeyedDataSource
import com.xxxxls.adapter.paging.LoadErrorCallback
import com.xxxxls.adapter.paging.OnListStatusListener
import com.xxxxls.adapter.paging.XSuperListStatus

/**
 * ItemKeyedLoadInitialCallback
 * @author Max
 * @date 2019-12-20.
 */
class ItemKeyedLoadInitialCallback<Value>(
    val callback: ItemKeyedDataSource.LoadInitialCallback<Value>,
    val status: OnListStatusListener?,
    private val retry: (() -> Unit)? = null
) :
    ItemKeyedDataSource.LoadInitialCallback<Value>(), LoadErrorCallback {

    override fun onResult(data: List<Value>) {
        callback.onResult(data)
        status?.onListStatusChange(XSuperListStatus.InitializeSuccess())
    }

    override fun onResult(data: List<Value>, position: Int, totalCount: Int) {
        callback.onResult(data)
        status?.onListStatusChange(XSuperListStatus.InitializeSuccess())
    }

    override fun onError(throwable: Throwable?) {
        status?.onListStatusChange(XSuperListStatus.InitializeError(retry))
    }

}