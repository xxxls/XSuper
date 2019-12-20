package com.xxxxls.adapter.paging.page_keyed

import androidx.paging.DataSource

/**
 * XSuperPageKeyedDataSourceFactory
 * @author Max
 * @date 2019-12-17.
 */
class XSuperPageKeyedDataSourceFactory<Key, Value>(val dataSource: IPageKeyedDataSource<Key, Value>) :
    DataSource.Factory<Key, Value>() {
    override fun create(): DataSource<Key, Value> {
        return XSuperPageKeyedDataSource(dataSource)
    }
}