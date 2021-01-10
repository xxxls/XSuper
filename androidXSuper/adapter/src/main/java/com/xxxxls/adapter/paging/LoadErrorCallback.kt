package com.xxxxls.adapter.paging

/**
 * 加载失败-回调
 * @author Max
 * @date 2019-12-23.
 */
interface LoadErrorCallback {

    /**
     * 错误
     * @param throwable 错误信息
     */
    fun onError(throwable: Throwable?)
}