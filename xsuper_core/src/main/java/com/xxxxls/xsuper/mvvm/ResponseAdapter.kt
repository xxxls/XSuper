package com.xxxxls.xsuper.mvvm

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
     */
    fun <T> toResult(response: XSuperResponse<T>): XSuperResult<T>

    /**
     * 异常转换为响应结果
     */
    fun toResult(throwable: Throwable): XSuperResult<Nothing>
}