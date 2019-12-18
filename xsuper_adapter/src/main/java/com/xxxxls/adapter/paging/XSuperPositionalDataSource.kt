package com.xxxxls.adapter.paging

import androidx.paging.PositionalDataSource

/**
 *
 * @author Max
 * @date 2019-12-17.
 */
class XSuperPositionalDataSource<T> (val dataSource: IPositionalDataSource<T>): PositionalDataSource<T>() {

    override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<T>) {
        dataSource.loadRange(params, callback)
    }

    override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<T>) {
        dataSource.loadInitial(params, callback)
    }
}
