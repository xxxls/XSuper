package com.xxxxls.adapter.paging.positional

import androidx.paging.PositionalDataSource

/**
 * PositionalDataSource
 * @author Max
 * @date 2019-12-17.
 */
class XSuperPositionalDataSource<T>(val dataSource: IPositionalDataSource<T>) :
    PositionalDataSource<T>() {

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<T>) {
        dataSource.loadRange(params, XSuperPositionalLoadRangeCallback(callback, dataSource))
    }

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<T>) {
        dataSource.loadInitial(params, XSuperPositionalLoadInitialCallback(callback, dataSource))
    }
}
