package com.xxxxls.example.ui.network.data.api

import com.xxxxls.example.ui.network.data.bean.ArticleItemBean
import com.xxxxls.module_base.net.response.BaseListResponse
import retrofit2.http.GET

/**
 * 示例 - 网络请求
 * @author Max
 * @date 2020/11/25.
 */
interface ExampleApi {

    /**
     * 获取文章列表
     */
    @GET("/article/list/1/json")
    suspend fun getArticleList(): BaseListResponse<ArticleItemBean>
}