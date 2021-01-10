package com.xxxxls.adapter.paging.positional

import androidx.paging.PositionalDataSource
import com.xxxxls.adapter.paging.OnListStatusListener
import com.xxxxls.adapter.paging.XSuperListStatus

/**
 * PositionalDataSource
 * @author Max
 * @date 2019-12-17.
 */
class PositionalDataSource<T>(
    val dataSource: IPositionalDataSource<T>,
    val statusListener: OnListStatusListener?
) :
    PositionalDataSource<T>() {

    //重试操作
    var retry: (() -> Unit)? = null

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<T>) {
        statusListener?.onListStatusChange(XSuperListStatus.LoadMoreIn)
        retry = {
            loadRange(params, callback)
        }
        dataSource.loadRange(
            params,
            PositionalLoadRangeCallback(callback, statusListener, retry)
        )
    }

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<T>) {
        statusListener?.onListStatusChange(XSuperListStatus.Initialize)
        retry = {
            loadInitial(params, callback)
        }
        dataSource.loadInitial(
            params,
            PositionalLoadInitialCallback(callback, statusListener, retry)
        )
    }
}
