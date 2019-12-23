package com.xxxxls.adapter.paging.item_keyed

import androidx.paging.ItemKeyedDataSource

/**
 * IItemKeyedDataSource
 * @author Max
 * @date 2019-12-19.
 */
interface IItemKeyedDataSource<Key, Value> {

    //此方法之后在用DataSource构建PageList的时候才会调用一次。用于进行加载初始化。
    fun loadInitial(
        params: ItemKeyedDataSource.LoadInitialParams<Key>,
        callback: ItemKeyedLoadInitialCallback<Value>
    )

    //在每次RecyclerView滑动到底部没有数据的时候就会调用此方法进行数据的加载。
    fun loadAfter(
        params: ItemKeyedDataSource.LoadParams<Key>,
        callback: ItemKeyedLoadCallback<Value>
    )

    //在每次RecyclerView滑动到顶部没有数据的时候就会调用此方法进行数据的加载。
    fun loadBefore(
        params: ItemKeyedDataSource.LoadParams<Key>,
        callback: ItemKeyedFrontLoadCallback<Value>
    )

    //这返回下一个loadAfter调用所需要用到的key。就相当于链表的指针。
    fun getKey(item: Value): Key

}