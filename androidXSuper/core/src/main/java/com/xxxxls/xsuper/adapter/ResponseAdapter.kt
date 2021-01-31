package com.xxxxls.xsuper.adapter

import com.xxxxls.xsuper.model.SuperResponse
import com.xxxxls.xsuper.model.SuperResult

/**
 * 响应适配器
 * @author Max
 * @date 2020/11/27.
 */
interface ResponseAdapter {

    /**
     * 获取接口响应体
     */
    fun <T> getResponseBody(response: SuperResponse<T>): T

    /**
     * 接口响应转换为响应结果
     * (根据状态码等区分出成功与失败)
     */
    fun <T> responseToResult(response: SuperResponse<T>): SuperResult<T>

    /**
     * 请求过程的异常转换为响应结果
     */
    fun throwableToResult(throwable: Throwable): SuperResult<Nothing>
}