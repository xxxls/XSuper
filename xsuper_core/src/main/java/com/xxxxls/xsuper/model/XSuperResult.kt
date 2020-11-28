package com.xxxxls.xsuper.model

import com.xxxxls.xsuper.exceptions.XSuperException
import com.xxxxls.xsuper.callback.XSuperCallBack

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
    data class Success<out T>(val data: T) : XSuperResult<T>()

    /**
     * 失败
     * @param throwable
     */
    class Failure(val throwable: Throwable) : XSuperResult<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Failure -> "Failure[exception=$throwable]"
        }
    }
}


/**
 * 成功
 */
inline fun <reified T> XSuperResult<T>.doSuccess(success: (T) -> Unit): XSuperResult<T> {
    if (this is XSuperResult.Success) {
        success(data)
    }
    return this
}

/**
 * 失败
 */
inline fun <reified T> XSuperResult<T>.doFailure(failure: (Throwable) -> Unit): XSuperResult<T> {
    if (this is XSuperResult.Failure) {
        failure(throwable)
    }
    return this
}

/**
 * 转换为成功结果
 */
fun <T> T.toSuccessResult(): XSuperResult.Success<T> {
    return XSuperResult.Success<T>(this)
}

/**
 * 转换为失败结果
 */
fun <T : XSuperException> T.toFailureResult(): XSuperResult.Failure {
    return XSuperResult.Failure(this)
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
            callBack.onError(this.throwable)
        }
    }
}


/**
 * 是否成功
 */
fun XSuperResult<*>.isSuccess(): Boolean {
    return this is XSuperResult.Success<*>
}
