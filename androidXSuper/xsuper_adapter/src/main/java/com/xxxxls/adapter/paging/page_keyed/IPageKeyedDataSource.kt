package com.xxxxls.adapter.paging.page_keyed

import androidx.paging.PageKeyedDataSource

/**
 * IPageKeyedDataSource
 * @author Max
 * @date 2019-12-19.
 */
interface IPageKeyedDataSource<Key, Value> {

    //此方法之后在用DataSource构建PageList的时候才会调用一次。用于进行加载初始化。
    fun loadInitial(
        params: PageKeyedDataSource.LoadInitialParams<Key>,
        callback: PageKeyedLoadInitialCallback<Key, Value>
    )

    //在每次RecyclerView滑动到底部没有数据的时候就会调用此方法进行数据的加载。
    fun loadAfter(
        params: PageKeyedDataSource.LoadParams<Key>,
        callback: PageKeyedLoadCallback<Key, Value>
    )

    //在每次RecyclerView滑动到顶部没有数据的时候就会调用此方法进行数据的加载。
    fun loadBefore(
        params: PageKeyedDataSource.LoadParams<Key>,
        callback: PageKeyedFrontLoadCallback<Key, Value>
    )

}