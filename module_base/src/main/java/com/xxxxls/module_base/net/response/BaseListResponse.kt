package com.xxxxls.module_base.net.response

/**
 * 基础列表响应体
 * @author Max
 * @date 2019-11-28.
 */
class BaseListResponse<out T> : BaseResponse<ListResponse<T>>() {

    /**
     * 获取非空的数据列表
     */
    fun getListNoNull(): List<T> {
        return data?.datas ?: emptyList()
    }
}