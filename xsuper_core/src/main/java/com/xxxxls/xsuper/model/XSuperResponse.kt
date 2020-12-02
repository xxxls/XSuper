package com.xxxxls.xsuper.model


/**
 * Super - 接口请求响应
 * @author Max
 * @date 2019-11-28.
 */
interface XSuperResponse<out T> {

    /**
     * 是否成功
     */
    fun isSuccess(): Boolean

    /**
     * 真实有效的结果（去除了基础属性后的数据）
     */
    fun getBody(): T?

}