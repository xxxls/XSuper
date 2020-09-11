package com.xxxxls.example.ui.home
import com.xxxxls.example.bean.ArticleBean
import com.xxxxls.example.net.HomeApis
import com.xxxxls.module_base.net.BaseLiveData
import com.xxxxls.module_base.net.FastApiViewModel
import com.xxxxls.module_base.net.response.ListResponse

/**
 * 首页文章列表
 * @author Max
 * @date 2019-12-07.
 */
class HomeArticleListViewModel : FastApiViewModel<HomeApis>() {

    val listLiveData: BaseLiveData<ListResponse<ArticleBean>> by lazy {
        BaseLiveData<ListResponse<ArticleBean>>()
    }


    fun refresh() {
        requestApi(listLiveData) {
            it.getHomeArticleListAsync(0)
        }
    }

    fun loadMore() {

    }

}