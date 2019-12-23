package com.xxxxls.adapter.paging

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource

/**
 * XSuperDataSourceFactory
 * @author Max
 * @date 2019-12-20.
 */
abstract class XSuperDataSourceFactory<Key, Value> :
    DataSource.Factory<Key, Value>() {

    //dataSource
    val dataSourceLiveData: MutableLiveData<DataSource<Key, Value>> by lazy {
        MutableLiveData<DataSource<Key, Value>>()
    }

    //列表状态改变事件
    var statusListener: OnListStatusListener? = null

    final override fun create(): DataSource<Key, Value> {
        val dataSource = createDataSource()
        dataSourceLiveData.postValue(dataSource)
        return dataSource
    }

    abstract fun createDataSource(): DataSource<Key, Value>

}