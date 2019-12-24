package com.xxxxls.example.ui.paging.page_keyed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import com.xxxxls.adapter.paging.XSuperListStatus
import com.xxxxls.adapter.paging.XSuperPaging
import com.xxxxls.adapter.paging.page_keyed.*
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
class PageKeyedViewModel : FastApiViewModel<HomeApis>(HomeApis::class.java),
    IPageKeyedDataSource<Int, TestPagingBean> {


    val paging: XSuperPaging<Int, TestPagingBean> by lazy {
        XSuperPaging(
            XSuperPageKeyedDataSourceFactory(this),
            PagedList.Config.Builder().apply {
                this.setPageSize(20)
                this.setInitialLoadSizeHint(20)
                this.setPrefetchDistance((20 / 5))
                this.setEnablePlaceholders(false)
            }.build(),
            listStatusLiveData
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
        params: PageKeyedDataSource.LoadInitialParams<Int>,
        callback: PageKeyedLoadInitialCallback<Int, TestPagingBean>
    ) {
        requestApi(object : XSuperCallBack<ListResponse<TestPagingBean>> {
            override fun onSuccess(_result: ListResponse<TestPagingBean>) {
                val result = testData(0)
                val value = (0..8).random()
                if (value % 3 == 0) {
                    callback.onResult(ArrayList(), 0, 0)
                } else {
                    val previousPageKey = if (value % 2 == 0) 1 else null
                    callback.onResult(result.datas, previousPageKey, result.datas.last().id)
                }
            }

            override fun onError(exception: XSuperException) {
                callback.onError(exception)
            }

        }) {
            it.getTestPagingListAsync(0)
        }
    }

    override fun loadAfter(
        params: PageKeyedDataSource.LoadParams<Int>,
        callback: PageKeyedLoadCallback<Int, TestPagingBean>
    ) {
        requestApi(object : XSuperCallBack<ListResponse<TestPagingBean>> {
            override fun onSuccess(_result: ListResponse<TestPagingBean>) {
                val result = testData(params.key)
                if (params.key > 100) {
                    callback.onResult(ArrayList(), null)
//                    callback.onError(null)
                    return
                }
                callback.onResult(result.datas, result.datas.last().id)
            }

            override fun onError(exception: XSuperException) {
                callback.onError(exception)
            }

        }) {
            it.getTestPagingListAsync(params.key!!)
        }
    }

    override fun loadBefore(
        params: PageKeyedDataSource.LoadParams<Int>,
        callback: PageKeyedFrontLoadCallback<Int, TestPagingBean>
    ) {
        requestApi(object : XSuperCallBack<ListResponse<TestPagingBean>> {
            override fun onSuccess(_result: ListResponse<TestPagingBean>) {
                val result = testDataBefore(params.key)
                if (params.key < -100) {
                    callback.onResult(ArrayList(), null)
                    return
                }
                callback.onResult(result.datas, result.datas.first().id)
            }

            override fun onError(exception: XSuperException) {
                callback.onError(exception)
            }
        }) {
            it.getTestPagingListAsync(params.key!!)
        }
    }


    /**
     * 往下加载数据
     */
    private fun testData(position: Int): ListResponse<TestPagingBean> {
        val list = ArrayList<TestPagingBean>()
        val startIndex = position + 1
        val endIndex = startIndex + 20

        for (index in startIndex until endIndex) {
            list.add(TestPagingBean(index, "author:$position", "item#$index"))
        }

        return ListResponse(position, list, 0, false, 5, list.size, 5 * 20)
    }

    /**
     * 往上加载数据
     */
    private fun testDataBefore(position: Int): ListResponse<TestPagingBean> {
        val list = ArrayList<TestPagingBean>()
        val endIndex = position
        val startIndex = endIndex + (20 * -1) + 1

        for (index in startIndex until endIndex) {
            list.add(TestPagingBean(index, "author:$position", "item#$index"))
        }

        return ListResponse(position, list, 0, false, 5, list.size, 5 * 20)
    }
}