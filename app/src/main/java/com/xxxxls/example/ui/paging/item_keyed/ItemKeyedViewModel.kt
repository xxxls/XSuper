package com.xxxxls.example.ui.paging.item_keyed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.ItemKeyedDataSource
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import com.xxxxls.adapter.paging.XSuperListStatus
import com.xxxxls.adapter.paging.XSuperPaging
import com.xxxxls.adapter.paging.item_keyed.*
import com.xxxxls.adapter.paging.positional.XSuperPositionalDataSourceFactory
import com.xxxxls.adapter.paging.positional.IPositionalDataSource
import com.xxxxls.adapter.paging.positional.PositionalLoadInitialCallback
import com.xxxxls.adapter.paging.positional.PositionalLoadRangeCallback
import com.xxxxls.example.bean.TestPagingBean
import com.xxxxls.example.net.HomeApis
import com.xxxxls.module_base.net.FastApiViewModel
import com.xxxxls.module_base.net.response.ListResponse
import com.xxxxls.utils.L
import com.xxxxls.xsuper.exceptions.XSuperException
import com.xxxxls.xsuper.net.callback.XSuperCallBack

/**
 * @author Max
 * @date 2019-12-07.
 */
class ItemKeyedViewModel : FastApiViewModel<HomeApis>(HomeApis::class.java),
    IItemKeyedDataSource<Int, TestPagingBean> {

    val paging: XSuperPaging<Int, TestPagingBean> by lazy {
        XSuperPaging(
            XSuperItemKeyedDataSourceFactory(this),
            PagedList.Config.Builder().apply {
                this.setPageSize(20)
                this.setInitialLoadSizeHint(20)
                this.setPrefetchDistance((20 / 5))
                this.setEnablePlaceholders(false)
            }.build(),
            listStatusLiveData,
            builder = {
                it.setInitialLoadKey(0)
            }
        )
    }

    val listLiveData: LiveData<PagedList<TestPagingBean>> by lazy {
        paging.build()
    }

    val listStatusLiveData: MutableLiveData<XSuperListStatus> by lazy {
        MutableLiveData<XSuperListStatus>()
    }

    fun refresh() {
        L.e("refresh() -> ")
        paging.refresh()
    }

    fun loadMore() {
        L.e("loadMore() -> ")
        val isRetry = paging.retry()
        if (!isRetry) {
            L.e("loadMore() -> false")
            //调用重试失败，重新刷新下当前状态
            listStatusLiveData.postValue(paging.status)
        } else {
            L.e("loadMore() -> true")
        }
    }

    override fun loadInitial(
        params: ItemKeyedDataSource.LoadInitialParams<Int>,
        callback: ItemKeyedLoadInitialCallback<TestPagingBean>
    ) {
        requestApi(object : XSuperCallBack<ListResponse<TestPagingBean>> {
            override fun onSuccess(_result: ListResponse<TestPagingBean>) {
                val result = testData(0)
                val value = (1..3).random()
                if (value % 2 == 0) {
                    callback.onResult(result.datas, 0, result.total)
                } else {
                    callback.onResult(ArrayList(), 0, 0)
                }
            }

            override fun onError(exception: XSuperException) {
                callback.onError(exception)
            }

        }) {
            params.requestedInitialKey
            it.getTestPagingListAsync(0)
        }
    }

    override fun loadAfter(
        params: ItemKeyedDataSource.LoadParams<Int>,
        callback: ItemKeyedLoadCallback<TestPagingBean>
    ) {
        requestApi(object : XSuperCallBack<ListResponse<TestPagingBean>> {
            override fun onSuccess(_result: ListResponse<TestPagingBean>) {
                val result = testData(params.key)
                if (params.key > 100) {
                    callback.onResult(ArrayList())
//                    callback.onError(null)
                    return
                }
                callback.onResult(result.datas)
            }

            override fun onError(exception: XSuperException) {
                callback.onError(exception)
            }

        }) {
            it.getTestPagingListAsync(params.key!!)
        }
    }

    override fun loadBefore(
        params: ItemKeyedDataSource.LoadParams<Int>,
        callback: ItemKeyedFrontLoadCallback<TestPagingBean>
    ) {
        requestApi(object : XSuperCallBack<ListResponse<TestPagingBean>> {
            override fun onSuccess(_result: ListResponse<TestPagingBean>) {
                val result = testData(params.key)
                if (params.key > 40) {
                    callback.onResult(ArrayList())
//                    callback.onError(null)
                    return
                }
                callback.onResult(result.datas)
            }

            override fun onError(exception: XSuperException) {
                callback.onError(exception)
            }
        }) {
            it.getTestPagingListAsync(params.key!!)
        }
    }

    override fun getKey(item: TestPagingBean): Int {
        return item.id
    }

    private fun testData(position: Int): ListResponse<TestPagingBean> {
        val list = ArrayList<TestPagingBean>()
        val num = if (position > 0) 1 else -1
        for (index in position until (position + 20)) {
            val value = index * num
            list.add(TestPagingBean(position, "author:$position", "item#$value"))
        }
        return ListResponse(position, list, 0, false, 5, list.size, 5 * 20)
    }
}