package com.xxxxls.module_base.network.response

/**
 * 列表响应体
 * @author Max
 * @date 2019-11-28.
 */
data class ListResponse<out T>(
    val curPage: Int?,
    val datas: List<T>?,
    val offset: Int?,
    val over: Boolean?,
    val pageCount: Int?,
    val size: Int?,
    private val total: Int?,
    val hasNextPage: Boolean = true
) : IListResponse<T> {

    override fun isHasNextPage(): Boolean {
        // TODO 待完善
        return hasNextPage
    }

    override fun getNextPage(): Int? {
        return null
    }

    override fun getTotal(): Int? {
        return total
    }

    override fun isEmpty(): Boolean {
        return datas.isNullOrEmpty()
    }

    override fun getList(): List<T>? {
        return datas
    }

}