package com.xxxxls.xsuper.model


/**
 * Super - 接口请求响应
 * @author Max
 * @date 2019-11-28.
 */
interface SuperResponse<out T> {

    /**
     * 是否成功
     */
    fun isSuccess(): Boolean

    /**
     * 真实有效的结果（去除了基础属性后的数据）
     */
    fun getBody(): T?


    /**
     * 成功的处理
     */
    fun doSuccess(block: (T?) -> Unit) {
        if (isSuccess()) {
            block.invoke(getBody())
        }
    }

    /**
     * 成功且结果不为空的处理
     */
    fun doSuccessNotEmpty(block: (T) -> Unit) {
        if (isSuccess()) {
            getBody()?.let {
                block.invoke(it)
            }
        }
    }

    /**
     * 失败的处理
     */
    fun doFailure(block: (SuperResponse<T>) -> Unit) {
        if (!isSuccess()) {
            block.invoke(this)
        }
    }
}