package com.xxxxls.adapter.paging.item_keyed

import androidx.paging.ItemKeyedDataSource

/**
 * ItemKeyedDataSource
 * @author Max
 * @date 2019-12-17.
 */
class XSuperItemKeyedDataSource<Key, Value>(val dataSource: IItemKeyedDataSource<Key, Value>) :
    ItemKeyedDataSource<Key, Value>() {

    override fun loadInitial(params: LoadInitialParams<Key>, callback: LoadInitialCallback<Value>) {
        dataSource.loadInitial(params, callback)
    }

    override fun loadAfter(params: LoadParams<Key>, callback: LoadCallback<Value>) {
        dataSource.loadAfter(params, callback)
    }

    override fun loadBefore(params: LoadParams<Key>, callback: LoadCallback<Value>) {
        dataSource.loadBefore(params, callback)
    }

    override fun getKey(item: Value): Key {
        return dataSource.getKey(item)
    }

}
