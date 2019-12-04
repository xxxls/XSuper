package com.xxxxls.xsuper.net

import com.xxxxls.xsuper.exceptions.XSuperException

/**
 * super - 响应结果类（成功/失败）
 * @author Max
 * @date 2019-11-28.
 */
sealed class XSuperResult<out T> {

    /**
     * 成功
     * @param data 成功的结果
     */
    class Success<T>(val data: T) : XSuperResult<T>()

    /**
     * 失败
     * @param exception 异常信息
     */
    class Error(val exception: XSuperException) : XSuperResult<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
        }
    }
}