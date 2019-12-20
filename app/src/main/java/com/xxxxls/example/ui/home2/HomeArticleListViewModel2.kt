package com.xxxxls.example.ui.home2

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import com.xxxxls.adapter.paging.XSuperListStatus
import com.xxxxls.adapter.paging.XSuperPaging
import com.xxxxls.adapter.paging.positional.XSuperPositionalDataSourceFactory
import com.xxxxls.adapter.paging.positional.IPositionalDataSource
import com.xxxxls.adapter.paging.positional.XSuperPositionalLoadInitialCallback
import com.xxxxls.adapter.paging.positional.XSuperPositionalLoadRangeCallback
import com.xxxxls.example.bean.ArticleBean
import com.xxxxls.example.net.HomeApis
import com.xxxxls.module_base.net.BaseLiveData
import com.xxxxls.module_base.net.FastApiViewModel
import com.xxxxls.module_base.net.response.ListResponse
import com.xxxxls.utils.L
import com.xxxxls.xsuper.exceptions.XSuperException
import com.xxxxls.xsuper.net.callback.XSuperCallBack

/**
 * 首页文章列表
 * @author Max
 * @date 2019-12-07.
 */
class HomeArticleListViewModel2 : FastApiViewModel<HomeApis>(HomeApis::class.java),
    IPositionalDataSource<ArticleBean> {

    private val listStatusLiveData: MutableLiveData<XSuperListStatus> by lazy {
        MutableLiveData<XSuperListStatus>()
    }

    val paging: XSuperPaging<Int, ArticleBean> by lazy {
        XSuperPaging(
            XSuperPositionalDataSourceFactory(this),
            PagedList.Config.Builder().apply {
                this.setPageSize(20)
                this.setInitialLoadSizeHint(20)
                this.setPrefetchDistance((20 / 5))
                this.setEnablePlaceholders(false)
            }.build()
        )
    }
    val listLiveData: LiveData<PagedList<ArticleBean>> by lazy {
        paging.build()
    }

    fun refresh() {
        L.e("refresh() -> ")
    }

    fun loadMore() {
        L.e("loadMore() -> ")
    }

    override fun loadRange(
        params: PositionalDataSource.LoadRangeParams,
        callback: XSuperPositionalLoadRangeCallback<ArticleBean>
    ) {
        L.e("loadRange() -> ${params.startPosition}")

        requestApi(object : XSuperCallBack<ListResponse<ArticleBean>> {
            override fun onSuccess(result: ListResponse<ArticleBean>) {
                if (params.startPosition > 140) {
                    callback.onResult(ArrayList())
                    return
                }
                callback.onResult(result.datas)
            }

            override fun onError(exception: XSuperException) {
            }

        }) {
            it.getHomeArticleListAsync(params.startPosition)
        }
    }

    override fun loadInitial(
        params: PositionalDataSource.LoadInitialParams,
        callback: XSuperPositionalLoadInitialCallback<ArticleBean>
    ) {
        L.e("loadInitial() -> ${params.requestedStartPosition}")
        requestApi(object : XSuperCallBack<ListResponse<ArticleBean>> {
            override fun onSuccess(result: ListResponse<ArticleBean>) {
                callback.onResult(result.datas, 0, result.total)
            }

            override fun onError(exception: XSuperException) {
            }

        }) {
            it.getHomeArticleListAsync(params.requestedStartPosition)
        }
    }

    override fun onListStatusChange(status: XSuperListStatus) {
        listStatusLiveData.postValue(status)
    }

}