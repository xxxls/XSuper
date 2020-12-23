package com.xxxxls.adapter.paging.positional

import androidx.paging.DataSource
import com.xxxxls.adapter.paging.XSuperDataSourceFactory

/**
 * XSuperPageKeyedDataSourceFactory
 * @author Max
 * @date 2019-12-17.
 */
class XSuperPositionalDataSourceFactory<T>(val dataSource: IPositionalDataSource<T>) :
    XSuperDataSourceFactory<Int, T>() {

    override fun createDataSource(): DataSource<Int, T> {
        return PositionalDataSource(dataSource,statusListener)
    }
}