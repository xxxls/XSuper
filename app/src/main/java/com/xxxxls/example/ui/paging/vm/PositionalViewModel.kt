package com.xxxxls.example.ui.paging.vm

import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import com.xxxxls.adapter.paging.XSuperPaging
import com.xxxxls.adapter.paging.positional.XSuperPositionalDataSourceFactory
import com.xxxxls.adapter.paging.positional.IPositionalDataSource
import com.xxxxls.adapter.paging.positional.PositionalLoadInitialCallback
import com.xxxxls.adapter.paging.positional.PositionalLoadRangeCallback
import com.xxxxls.example.bean.TestPagingBean
import com.xxxxls.module_base.net.response.ListResponse
import com.xxxxls.utils.L
import com.xxxxls.xsuper.exceptions.XSuperException
import com.xxxxls.xsuper.net.callback.XSuperCallBack

/**
 * PositionalViewModel
 * @author Max
 * @date 2019-12-07.
 */
class PositionalViewModel : BasePagingListViewModel(),
    IPositionalDataSource<TestPagingBean> {

    private val paging: XSuperPaging<Int, TestPagingBean> by lazy {
        XSuperPaging(
            XSuperPositionalDataSourceFactory(this),
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

    override fun loadRange(
        params: PositionalDataSource.LoadRangeParams,
        callback: PositionalLoadRangeCallback<TestPagingBean>
    ) {
        L.e("loadRange() -> ${params.startPosition}")

        requestApi(object : XSuperCallBack<ListResponse<TestPagingBean>> {
            override fun onSuccess(_result: ListResponse<TestPagingBean>) {

                val result = testData(params.startPosition)
                if (params.startPosition > 100) {
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
            it.getTestPagingListAsync(params.startPosition)
        }
    }

    override fun loadInitial(
        params: PositionalDataSource.LoadInitialParams,
        callback: PositionalLoadInitialCallback<TestPagingBean>
    ) {
        L.e("loadInitial() -> ${params.requestedStartPosition}")
        requestApi(object : XSuperCallBack<ListResponse<TestPagingBean>> {
            override fun onSuccess(_result: ListResponse<TestPagingBean>) {

                val result = testData(0)
                val value = (0..10).random()
                if (value % 4 == 0) {
                    callback.onResult(ArrayList(), 0, 0)
                } else {
                    callback.onResult(result.datas, 0, result.total)
                }
            }

            override fun onError(exception: XSuperException) {
                callback.onError(exception)
            }

        }) {
            it.getTestPagingListAsync(params.requestedStartPosition)
        }
    }

}