package com.xxxxls.adapter.paging

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.xxxxls.utils.L

/**
 * Paging
 * @author Max
 * @date 2019-12-19.
 */
class XSuperPaging<Key, Value>(
    val dataSourceFactory: XSuperDataSourceFactory<Key, Value>,
    val config: PagedList.Config
) {

    private var retry: (() -> Any)? = null

    /**
     * 刷新
     */
    fun refresh(){
        dataSourceFactory.dataSourceLiveData.value?.invalidate()
    }

    fun loadAfter(){

    }

    fun loadBefore(){

    }

    fun build(pagedListBuilder: ((livePagedListBuilder: LivePagedListBuilder<Key, Value>) -> Unit)? = null): LiveData<PagedList<Value>> {
        return LivePagedListBuilder(
            dataSourceFactory,
            config
        ).apply {
            setBoundaryCallback(object : PagedList.BoundaryCallback<Value>() {
                override fun onItemAtEndLoaded(itemAtEnd: Value) {
                    super.onItemAtEndLoaded(itemAtEnd)
                    L.e("BoundaryCallback() -> onItemAtEndLoaded() :${itemAtEnd.toString()}")
                }

                override fun onItemAtFrontLoaded(itemAtFront: Value) {
                    super.onItemAtFrontLoaded(itemAtFront)
                    L.e("BoundaryCallback() -> onItemAtFrontLoaded() :${itemAtFront.toString()}")
                }

                override fun onZeroItemsLoaded() {
                    super.onZeroItemsLoaded()
                    L.e("BoundaryCallback -> onZeroItemsLoaded()")
                }
            })
            pagedListBuilder?.invoke(this)
        }.build()
    }
}