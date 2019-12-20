package com.xxxxls.adapter.paging.positional

import androidx.paging.PositionalDataSource
import com.xxxxls.adapter.paging.IListStatus

/**
 *
 * @author Max
 * @date 2019-12-20.
 */
class XSuperPositionalLoadInitialCallback<T>(
    val callback: PositionalDataSource.LoadInitialCallback<T>,
    val status: IListStatus
) :
    PositionalDataSource.LoadInitialCallback<T>() {

    override fun onResult(data: List<T>, position: Int, totalCount: Int) {
        callback.onResult(data, position, totalCount)
    }

    override fun onResult(data: List<T>, position: Int) {
        callback.onResult(data, position, position)
    }

}