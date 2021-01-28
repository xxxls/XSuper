package com.xxxxls.module_base.network.response

/**
 * 列表数据响应
 * @author Max
 * @date 1/28/21.
 */
interface IListResponse<out T> {

    /**
     * 是否还有下一页
     */
    fun isHasNextPage(): Boolean

    /**
     * 下一页页码
     */
    fun getNextPage(): Int?

    /**
     * 获取总数量
     */
    fun getTotal(): Int?

    /**
     * 是否为空列表
     */
    fun isEmpty(): Boolean

    /**
     * 获取数据
     */
    fun getList(): List<T>?

    /**
     * 获取非空的数据列表
     */
    fun getListNoNull(): List<T> {
        return getList() ?: emptyList()
    }
}