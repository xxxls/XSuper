package com.xxxxls.xsuper.adapter

import com.xxxxls.xsuper.component.bridge.ComponentActionBridge
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

    /**
     * 分析结果
     * @param bridge 与组件的通信桥梁
     * @param result 结果
     */
    fun <T> analysisResult(
        bridge: ComponentActionBridge,
        result: XSuperResult<T>
    ): XSuperResult<T>
}