package com.xxxxls.example.ui.paging.data.api

import com.xxxxls.example.ui.paging.data.bean.PagingItemBean
import com.xxxxls.module_base.network.response.BaseListResponse
import retrofit2.http.GET

/**
 * 分页 - 网络请求
 * @author Max
 * @date 2020/12/23.
 */
interface PagingApi {

    /**
     * 获取文章列表
     */
    @GET("/article/list/1/json")
    suspend fun getArticleList(): BaseListResponse<PagingItemBean>
}