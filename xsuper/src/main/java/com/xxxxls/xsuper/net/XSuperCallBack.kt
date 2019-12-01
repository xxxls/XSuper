package com.xxxxls.xsuper.net

import com.xxxxls.xsuper.exceptions.XSuperException

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
     * @param exception 错误信息
     */
    fun onError(exception: XSuperException)
}