package com.xxxxls.example.ui.paging.positional

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import com.xxxxls.adapter.paging.XSuperListStatus
import com.xxxxls.adapter.paging.XSuperPaging
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
class PositionalViewModel : FastApiViewModel<HomeApis>(HomeApis::class.java),
    IPositionalDataSource<TestPagingBean> {

    val paging: XSuperPaging<Int, TestPagingBean> by lazy {
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
            it.getTestPagingListAsync(params.requestedStartPosition)
        }
    }


    private fun testData(position: Int): ListResponse<TestPagingBean> {
        val list = ArrayList<TestPagingBean>()
        for (index in position until (position + 20)) {
            list.add(TestPagingBean(position, "author:$position", "item#$index"))
        }
        return ListResponse(position, list, 0, false, 5, list.size, 5 * 20)
    }
}