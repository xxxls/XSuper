package com.xxxxls.example.ui.home2

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import com.xxxxls.adapter.paging.IPositionalDataSource
import com.xxxxls.example.bean.ArticleBean
import com.xxxxls.example.net.HomeApis
import com.xxxxls.module_base.net.FastApiViewModel
import com.xxxxls.module_base.net.response.ListResponse
import com.xxxxls.xsuper.exceptions.XSuperException
import com.xxxxls.xsuper.net.callback.XSuperCallBack

/**
 * 首页文章列表
 * @author Max
 * @date 2019-12-07.
 */
class HomeArticleListViewModel2 : FastApiViewModel<HomeApis>(HomeApis::class.java),
    IPositionalDataSource<ArticleBean> {

    val listLiveData: LiveData<PagedList<ArticleBean>> by lazy {
//        LivePagedListBuilder(DataSourceFactory(this), 8).build()
        LivePagedListBuilder(DataSourceFactory(this), PagedList.Config.Builder().apply {
            this.setPageSize(20)
            this.setInitialLoadSizeHint(20)
            this.setPrefetchDistance(20)
            this.setEnablePlaceholders(false)

        }.build()).build()
    }


    fun refresh() {
    }

    fun loadMore() {

    }

    override fun loadRange(
        params: PositionalDataSource.LoadRangeParams,
        callback: PositionalDataSource.LoadRangeCallback<ArticleBean>
    ) {
        requestApi(object : XSuperCallBack<ListResponse<ArticleBean>> {
            override fun onSuccess(result: ListResponse<ArticleBean>) {
                if(params.startPosition>100){
                    callback.onResult(ArrayList())
                    return
                }
                callback.onResult(result.datas)
            }

            override fun onError(exception: XSuperException) {
                callback.onResult(ArrayList())
            }

        }) {
            it.getHomeArticleListAsync(params.startPosition)
        }
    }

    override fun loadInitial(
        params: PositionalDataSource.LoadInitialParams,
        callback: PositionalDataSource.LoadInitialCallback<ArticleBean>
    ) {
        requestApi(object : XSuperCallBack<ListResponse<ArticleBean>> {
            override fun onSuccess(result: ListResponse<ArticleBean>) {
                callback.onResult(result.datas, 0, result.total)
            }

            override fun onError(exception: XSuperException) {
                callback.onResult(ArrayList(), 0, 0)
            }

        }) {
            it.getHomeArticleListAsync(params.requestedStartPosition)
        }
    }

}