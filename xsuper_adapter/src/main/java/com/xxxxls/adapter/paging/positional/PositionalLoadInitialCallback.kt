package com.xxxxls.adapter.paging.positional

import androidx.paging.PositionalDataSource
import com.xxxxls.adapter.paging.LoadErrorCallback
import com.xxxxls.adapter.paging.OnListStatusListener
import com.xxxxls.adapter.paging.XSuperListStatus

/**
 * InitialCallback
 * @author Max
 * @date 2019-12-20.
 */
class PositionalLoadInitialCallback<T>(
    val callback: PositionalDataSource.LoadInitialCallback<T>,
    val statusListener: OnListStatusListener?,
    private val retry: (() -> Unit)? = null
) :
    PositionalDataSource.LoadInitialCallback<T>(), LoadErrorCallback {

    override fun onResult(data: List<T>, position: Int, totalCount: Int) {
        callback.onResult(data, position, totalCount)
        statusListener?.onListStatusChange(XSuperListStatus.InitializeSuccess())
    }

    override fun onResult(data: List<T>, position: Int) {
        callback.onResult(data, position)
        statusListener?.onListStatusChange(XSuperListStatus.InitializeSuccess())
    }

    override fun onError(throwable: Throwable?) {
        statusListener?.onListStatusChange(XSuperListStatus.InitializeError(retry))
    }
}