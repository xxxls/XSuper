package com.xxxxls.example.net

import com.xxxxls.example.bean.ArticleBean
import com.xxxxls.module_base.net.response.BaseListResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.*

/**
 * 首页
 * @author Max
 * @date 2019-12-07.
 */
interface HomeApis {

    /**
     * 首页文章
     * @param page 页码
     */
    @GET("/article/list/{page}/json")
    fun getHomeArticleListAsync(
        @Path("page") page: Int
    ): Deferred<BaseListResponse<ArticleBean>>

}