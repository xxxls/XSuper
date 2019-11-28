package com.xxxxls.module_base.net.response

/**
 * 基础列表响应体
 * @author Max
 * @date 2019-11-28.
 */
data class BaseListResponse<out T>(
    val curPage: Int,
    val datas: List<T>,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int
)