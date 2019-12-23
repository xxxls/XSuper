package com.xxxxls.adapter.paging.positional

import androidx.paging.PositionalDataSource
import com.xxxxls.adapter.paging.LoadErrorCallback
import com.xxxxls.adapter.paging.OnListStatusListener
import com.xxxxls.adapter.paging.XSuperListStatus

/**
 * LoadRangeCallback
 * @author Max
 * @date 2019-12-20.
 */
class PositionalLoadRangeCallback<T>(
    val callback: PositionalDataSource.LoadRangeCallback<T>,
    val status: OnListStatusListener?,
    private val retry: (() -> Unit)? = null
) :
    PositionalDataSource.LoadRangeCallback<T>(), LoadErrorCallback {

    override fun onResult(data: List<T>) {
        callback.onResult(data)
        status?.onListStatusChange(XSuperListStatus.LoadMoreSuccess())
    }

    override fun onError(throwable: Throwable?) {
        status?.onListStatusChange(XSuperListStatus.LoadMoreError(retry))
    }

}