package com.xxxxls.example.ui.paging.vm

import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import com.xxxxls.adapter.paging.XSuperPaging
import com.xxxxls.adapter.paging.positional.XSuperPositionalDataSourceFactory
import com.xxxxls.adapter.paging.positional.IPositionalDataSource
import com.xxxxls.adapter.paging.positional.PositionalLoadInitialCallback
import com.xxxxls.adapter.paging.positional.PositionalLoadRangeCallback
import com.xxxxls.example.bean.TestPagingBean
import com.xxxxls.module_base.constants.Constants
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
                this.setPageSize(Constants.PAGE_SIZE)
                this.setInitialLoadSizeHint(Constants.PAGE_SIZE)
                this.setPrefetchDistance((Constants.PAGE_SIZE / 5))
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
        L.e("PositionalViewModel -> loadRange()")
        mHomeRepository.getTestPagingList(false,params.startPosition,object :XSuperCallBack<ListResponse<TestPagingBean>> {
            override fun onSuccess(result: ListResponse<TestPagingBean>?) {
                L.e("PositionalViewModel -> loadRange() -> onSuccess()")
                callback.onResult(result?.datas?:ArrayList())
            }

            override fun onError(exception: XSuperException) {
                L.e("PositionalViewModel -> loadRange() -> onError()")
                callback.onError(exception)
            }
        })
    }

    override fun loadInitial(
        params: PositionalDataSource.LoadInitialParams,
        callback: PositionalLoadInitialCallback<TestPagingBean>
    ) {
        L.e("PositionalViewModel -> loadInitial()")
        mHomeRepository.getTestPagingList(false,params.requestedStartPosition,object :XSuperCallBack<ListResponse<TestPagingBean>> {
            override fun onSuccess(result: ListResponse<TestPagingBean>?) {
                callback.onResult(result?.datas?:ArrayList(), 0, result?.total?:0)
                L.e("PositionalViewModel -> loadInitial() -> onSuccess()")
            }

            override fun onError(exception: XSuperException) {
                callback.onError(exception)
                L.e("PositionalViewModel -> loadInitial() -> onError()")
            }
        })
    }

}