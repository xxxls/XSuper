package com.xxxxls.xsuper.model

/**
 * Super - 接口响应
 * @author Max
 * @date 2019-11-28.
 */
interface XSuperResponse<out T> {

    /**
     * 是否成功
     */
    fun isSuccess(): Boolean

    /**
     * 真实结果
     */
    fun getBody(): T?

}