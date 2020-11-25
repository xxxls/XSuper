package com.xxxxls.xsuper.net

import com.xxxxls.xsuper.exceptions.XSuperException
import com.xxxxls.xsuper.net.callback.XSuperCallBack

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
    data class Success<T>(val data: T?) : XSuperResult<T>()

    /**
     * 失败
     * @param exception
     */
    class Failure(val exception: XSuperException) : XSuperResult<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Failure -> "Failure[exception=$exception]"
        }
    }
}


/**
 * Result to callback
 */
fun <T> XSuperResult<T>.callback(callBack: XSuperCallBack<T>) {
    when (this) {
        is XSuperResult.Success<T> -> {
            callBack.onSuccess(this.data)
        }
        is XSuperResult.Failure -> {
            callBack.onError(this.exception)
        }
    }
}


/**
 * 是否成功
 */
fun XSuperResult<*>.isSuccess(): Boolean {
    return this is XSuperResult.Success<*>
}
