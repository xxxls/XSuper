package com.xxxxls.adapter.paging.item_keyed

import androidx.paging.DataSource

/**
 * XSuperItemKeyedDataSourceFactory
 * @author Max
 * @date 2019-12-17.
 */
class XSuperItemKeyedDataSourceFactory<Key, Value>(val dataSource: IItemKeyedDataSource<Key, Value>) :
    DataSource.Factory<Key, Value>() {
    override fun create(): DataSource<Key, Value> {
        return XSuperItemKeyedDataSource(dataSource)
    }
}