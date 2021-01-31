package com.xxxxls.xsuper.model

import com.xxxxls.xsuper.callback.SuperCallBack

/**
 * super - 响应结果类（成功/失败）
 * @author Max
 * @date 2019-11-28.
 */
sealed class SuperResult<out T> {

    /**
     * 成功
     * @param data 成功的结果
     */
    data class Success<out T>(val data: T) : SuperResult<T>()

    /**
     * 失败
     * @param throwable
     */
    data class Failure(val throwable: Throwable) : SuperResult<Nothing>()

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
inline fun <reified T> SuperResult<T>.doSuccess(success: (T) -> Unit): SuperResult<T> {
    if (this is SuperResult.Success) {
        success(data)
    }
    return this
}

/**
 * 失败
 */
inline fun <reified T> SuperResult<T>.doFailure(failure: (Throwable) -> Unit): SuperResult<T> {
    if (this is SuperResult.Failure) {
        failure(throwable)
    }
    return this
}

/**
 * 转换为成功结果
 */
fun <T> T.toSuccessResult(): SuperResult.Success<T> {
    return SuperResult.Success<T>(this)
}

/**
 * 转换为失败结果
 */
fun <T : Throwable> T.toFailureResult(): SuperResult.Failure {
    return SuperResult.Failure(this)
}

/**
 * Result to callback
 */
fun <T> SuperResult<T>.callback(callBack: SuperCallBack<T>) {
    when (this) {
        is SuperResult.Success<T> -> {
            callBack.onSuccess(this.data)
        }
        is SuperResult.Failure -> {
            callBack.onFailure(this.throwable)
        }
    }
}


/**
 * 是否成功
 */
fun SuperResult<*>.isSuccess(): Boolean {
    return this is SuperResult.Success<*>
}
