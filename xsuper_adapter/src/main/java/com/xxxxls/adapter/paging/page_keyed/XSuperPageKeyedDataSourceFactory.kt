package com.xxxxls.adapter.paging.page_keyed

import androidx.paging.DataSource
import com.xxxxls.adapter.paging.XSuperDataSourceFactory

/**
 * XSuperPageKeyedDataSourceFactory
 * @author Max
 * @date 2019-12-17.
 */
class XSuperPageKeyedDataSourceFactory<Key, Value>(val dataSource: IPageKeyedDataSource<Key, Value>) :

    XSuperDataSourceFactory<Key, Value>() {

    override fun createDataSource(): DataSource<Key, Value> {
        return PageKeyedDataSource(dataSource, statusListener = statusListener)
    }
}