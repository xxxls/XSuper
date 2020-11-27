package com.xxxxls.xsuper.callback

import androidx.annotation.NonNull

/**
 * Super - 回调
 * @author Max
 * @date 2019-11-26.
 */
interface XSuperCallBack<T> {

    /**
     * 成功
     * @param result 结果
     */
    fun onSuccess(result: T)

    /**
     * 失败
     * @param throwable 错误信息
     */
    fun onError(throwable: Throwable)
}

/**
 * 转换
 */
fun <T, R> XSuperCallBack<T>.map(@NonNull mapFunction: (R) -> T): XSuperCallBack<R> {

    return object : XSuperCallBack<R> {
        override fun onSuccess(result: R) {
            this@map.onSuccess(mapFunction(result))
        }

        override fun onError(throwable: Throwable) {
            this@map.onError(throwable)
        }
    }
}