package com.xxxxls.adapter.paging.page_keyed

import androidx.paging.PageKeyedDataSource

/**
 * PageKeyedDataSource
 * @author Max
 * @date 2019-12-19.
 */
class XSuperPageKeyedDataSource<Key,Value>(val dataSource: IPageKeyedDataSource<Key,Value>) : PageKeyedDataSource<Key,Value>(){
    override fun loadInitial(
        params: LoadInitialParams<Key>,
        callback: LoadInitialCallback<Key, Value>
    ) {
        dataSource.loadInitial(params, callback)
    }

    override fun loadAfter(params: LoadParams<Key>, callback: LoadCallback<Key, Value>) {
        dataSource.loadAfter(params, callback)
    }

    override fun loadBefore(params: LoadParams<Key>, callback: LoadCallback<Key, Value>) {
        dataSource.loadBefore(params, callback)
    }

}