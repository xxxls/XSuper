package com.xxxxls.xsuper.adapter

import com.xxxxls.xsuper.model.XSuperResponse
import com.xxxxls.xsuper.model.XSuperResult

/**
 * 响应转换器
 * @author Max
 * @date 2020/11/27.
 */
interface ResponseAdapter {

    /**
     * 接口响应转换为响应结果
     * (根据状态码等区分出成功与失败)
     */
    fun <T> responseToResult(response: XSuperResponse<T>): XSuperResult<T>

    /**
     * 请求过程的异常转换为响应结果
     */
    fun throwableToResult(throwable: Throwable): XSuperResult<Nothing>
}