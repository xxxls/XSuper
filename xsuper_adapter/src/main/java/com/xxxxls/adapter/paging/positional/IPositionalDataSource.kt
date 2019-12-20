package com.xxxxls.adapter.paging.positional

import androidx.paging.PositionalDataSource
import com.xxxxls.adapter.paging.IListStatus

/**
 * PositionalDataSource
 * @author Max
 * @date 2019-12-17.
 */
interface IPositionalDataSource<T> : IListStatus {

    fun loadRange(
        params: PositionalDataSource.LoadRangeParams,
        callback: XSuperPositionalLoadRangeCallback<T>
    )

    fun loadInitial(
        params: PositionalDataSource.LoadInitialParams,
        callback: XSuperPositionalLoadInitialCallback<T>
    )
}
