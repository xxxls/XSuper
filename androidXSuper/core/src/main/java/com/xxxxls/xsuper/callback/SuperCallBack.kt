package com.xxxxls.xsuper.callback

import androidx.annotation.NonNull

/**
 * Super - 回调
 * @author Max
 * @date 2019-11-26.
 */
interface SuperCallBack<T> {

    /**
     * 成功
     * @param result 结果
     */
    fun onSuccess(result: T)

    /**
     * 失败
     * @param throwable 错误信息
     */
    fun onFailure(throwable: Throwable)
}

/**
 * 转换
 */
fun <T, R> SuperCallBack<T>.map(@NonNull mapFunction: (R) -> T): SuperCallBack<R> {

    return object : SuperCallBack<R> {
        override fun onSuccess(result: R) {
            this@map.onSuccess(mapFunction(result))
        }

        override fun onFailure(throwable: Throwable) {
            this@map.onFailure(throwable)
        }
    }
}