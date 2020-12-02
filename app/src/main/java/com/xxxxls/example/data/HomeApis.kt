package com.xxxxls.example.data

import com.xxxxls.example.bean.ArticleBean
import com.xxxxls.example.bean.TestPagingBean
import com.xxxxls.module_base.network.response.BaseListResponse
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


    /**
     * 测试接口
     * @param page 页码
     */
    @GET("/article/list/{page}/json")
    fun getTestPagingListAsync(
        @Path("page") page: Int
    ): Deferred<BaseListResponse<TestPagingBean>>

}