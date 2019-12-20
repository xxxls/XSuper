package com.xxxxls.adapter.paging.positional

import androidx.paging.PositionalDataSource
import com.xxxxls.adapter.paging.IListStatus

/**
 *
 * @author Max
 * @date 2019-12-20.
 */
class XSuperPositionalLoadRangeCallback<T>(
    val callback: PositionalDataSource.LoadRangeCallback<T>,
    val status: IListStatus
) :
    PositionalDataSource.LoadRangeCallback<T>() {

    override fun onResult(data: List<T>) {
        callback.onResult(data)
    }

}