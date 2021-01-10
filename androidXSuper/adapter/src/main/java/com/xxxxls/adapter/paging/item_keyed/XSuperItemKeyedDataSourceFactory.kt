package com.xxxxls.adapter.paging.item_keyed

import androidx.paging.DataSource
import com.xxxxls.adapter.paging.XSuperDataSourceFactory

/**
 * XSuperItemKeyedDataSourceFactory
 * @author Max
 * @date 2019-12-17.
 */
class XSuperItemKeyedDataSourceFactory<Key, Value>(val dataSource: IItemKeyedDataSource<Key, Value>) :
    XSuperDataSourceFactory<Key, Value>() {

    override fun createDataSource(): DataSource<Key, Value> {
        return ItemKeyedDataSource(dataSource, statusListener = statusListener)
    }
}