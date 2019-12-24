package com.xxxxls.example.ui.paging.vm

import androidx.paging.ItemKeyedDataSource
import androidx.paging.PagedList
import com.xxxxls.adapter.paging.XSuperPaging
import com.xxxxls.adapter.paging.item_keyed.*
import com.xxxxls.example.bean.TestPagingBean
import com.xxxxls.module_base.net.response.ListResponse
import com.xxxxls.xsuper.exceptions.XSuperException
import com.xxxxls.xsuper.net.callback.XSuperCallBack

/**
 * ItemKeyedViewModel
 * @author Max
 * @date 2019-12-07.
 */
class ItemKeyedViewModel : BasePagingListViewModel(),
    IItemKeyedDataSource<Int, TestPagingBean> {

    private val paging: XSuperPaging<Int, TestPagingBean> by lazy {
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

    override fun getXSuperPaging(): XSuperPaging<*, TestPagingBean> {
        return paging
    }

    override fun loadInitial(
        params: ItemKeyedDataSource.LoadInitialParams<Int>,
        callback: ItemKeyedLoadInitialCallback<TestPagingBean>
    ) {
        requestApi(object : XSuperCallBack<ListResponse<TestPagingBean>> {
            override fun onSuccess(_result: ListResponse<TestPagingBean>) {
                val result = testData(0)
                val value = (0..10).random()
                if (value % 4 == 0) {
                    callback.onResult(ArrayList(), 0, 0)
                } else {
                    callback.onResult(result.datas)
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
                val result = testDataBefore(params.key)
                if (params.key < -100) {
                    callback.onResult(ArrayList())
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

}