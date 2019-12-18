package com.xxxxls.adapter.paging

import androidx.paging.PositionalDataSource

/**
 *
 * @author Max
 * @date 2019-12-17.
 */

interface IPositionalDataSource<T>{

    fun loadRange(params: PositionalDataSource.LoadRangeParams, callback: PositionalDataSource.LoadRangeCallback<T>)

    fun loadInitial(params: PositionalDataSource.LoadInitialParams, callback: PositionalDataSource.LoadInitialCallback<T>)
}
