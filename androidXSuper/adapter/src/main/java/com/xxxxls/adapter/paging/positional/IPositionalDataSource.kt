package com.xxxxls.adapter.paging.positional

import androidx.paging.PositionalDataSource

/**
 * PositionalDataSource
 * @author Max
 * @date 2019-12-17.
 */
interface IPositionalDataSource<T> {

    fun loadRange(
        params: PositionalDataSource.LoadRangeParams,
        callback: PositionalLoadRangeCallback<T>
    )

    fun loadInitial(
        params: PositionalDataSource.LoadInitialParams,
        callback: PositionalLoadInitialCallback<T>
    )
}
