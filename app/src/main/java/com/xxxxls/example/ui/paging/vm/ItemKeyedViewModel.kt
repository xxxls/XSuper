package com.xxxxls.example.ui.paging.vm

import androidx.paging.ItemKeyedDataSource
import androidx.paging.PagedList
import com.xxxxls.adapter.paging.XSuperPaging
import com.xxxxls.adapter.paging.item_keyed.*
import com.xxxxls.example.bean.TestPagingBean
import com.xxxxls.module_base.net.response.ListResponse
import com.xxxxls.utils.L
import com.xxxxls.xsuper.exceptions.XSuperException
import com.xxxxls.xsuper.callback.XSuperCallBack

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
                it.setInitialLoadKey(-1)
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
        L.e("ItemKeyedViewModel -> loadInitial()")
        mHomeRepository.getTestPagingList(
            false,
            params.requestedInitialKey ?: -1,
            object : XSuperCallBack<ListResponse<TestPagingBean>> {
                override fun onSuccess(result: ListResponse<TestPagingBean>?) {
                    callback.onResult(result?.datas ?: ArrayList())
                    L.e("ItemKeyedViewModel -> loadInitial() -> onSuccess()")
                }

                override fun onError(exception: XSuperException) {
                    callback.onError(exception)
                    L.e("ItemKeyedViewModel -> loadInitial() -> onError()")
                }
            })
    }

    override fun loadAfter(
        params: ItemKeyedDataSource.LoadParams<Int>,
        callback: ItemKeyedLoadCallback<TestPagingBean>
    ) {
        L.e("ItemKeyedViewModel -> onError()")
        mHomeRepository.getTestPagingList(
            false,
            params.key!!,
            object : XSuperCallBack<ListResponse<TestPagingBean>> {
                override fun onSuccess(result: ListResponse<TestPagingBean>?) {
                    callback.onResult(result?.datas?:ArrayList())
                    L.e("ItemKeyedViewModel -> loadAfter() -> onSuccess()")
                }

                override fun onError(exception: XSuperException) {
                    callback.onError(exception)
                    L.e("ItemKeyedViewModel -> loadAfter() -> onError()")
                }
            })
    }

    override fun loadBefore(
        params: ItemKeyedDataSource.LoadParams<Int>,
        callback: ItemKeyedFrontLoadCallback<TestPagingBean>
    ) {
        L.e("ItemKeyedViewModel -> loadBefore()")
        mHomeRepository.getTestPagingList(
            true,
            params.key!!,
            object : XSuperCallBack<ListResponse<TestPagingBean>> {
                override fun onSuccess(result: ListResponse<TestPagingBean>?) {
                    callback.onResult(result?.datas?:ArrayList())
                    L.e("ItemKeyedViewModel -> loadBefore() -> onSuccess()")
                }

                override fun onError(exception: XSuperException) {
                    callback.onError(exception)
                    L.e("ItemKeyedViewModel -> loadBefore() -> onError()")
                }
            })
    }

    override fun getKey(item: TestPagingBean): Int {
        return item.id
    }

}