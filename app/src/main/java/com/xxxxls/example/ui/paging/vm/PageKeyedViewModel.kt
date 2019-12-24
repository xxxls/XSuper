package com.xxxxls.example.ui.paging.vm

import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import com.xxxxls.adapter.paging.XSuperPaging
import com.xxxxls.adapter.paging.page_keyed.*
import com.xxxxls.example.bean.TestPagingBean
import com.xxxxls.module_base.net.response.ListResponse
import com.xxxxls.xsuper.exceptions.XSuperException
import com.xxxxls.xsuper.net.callback.XSuperCallBack

/**
 * PageKeyedViewModel
 * @author Max
 * @date 2019-12-07.
 */
class PageKeyedViewModel : BasePagingListViewModel(),
    IPageKeyedDataSource<Int, TestPagingBean> {

    private val paging: XSuperPaging<Int, TestPagingBean> by lazy {
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
    override fun getXSuperPaging(): XSuperPaging<*, TestPagingBean> {
        return paging
    }

    override fun loadInitial(
        params: PageKeyedDataSource.LoadInitialParams<Int>,
        callback: PageKeyedLoadInitialCallback<Int, TestPagingBean>
    ) {
        requestApi(object : XSuperCallBack<ListResponse<TestPagingBean>> {
            override fun onSuccess(_result: ListResponse<TestPagingBean>) {
                val result = testData(0)
                val value = (0..10).random()
                if (value % 4 == 0) {
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

}