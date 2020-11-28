package com.xxxxls.module_base.support

import com.xxxxls.xsuper.component.bridge.ComponentActionBridge
import com.xxxxls.xsuper.exceptions.ApiException
import com.xxxxls.xsuper.exceptions.CodeException
import com.xxxxls.xsuper.exceptions.XSuperException
import com.xxxxls.xsuper.model.*
import com.xxxxls.xsuper.adapter.ResponseAdapter
import java.lang.NullPointerException

/**
 * 接口响应 转换器
 * @author Max
 * @date 2020/11/27.
 */
class ApiResponseAdapter : ResponseAdapter {

    /**
     * 接口响应转换为响应结果
     * (根据状态码等区分出成功与失败)
     */
    override fun <T> responseToResult(response: XSuperResponse<T>): XSuperResult<T> {
        if (response.isSuccess()) {
            response.getBody()?.let {
                return XSuperResult.Success<T>(data = it)
            } ?: let {
                return XSuperResult.Failure(XSuperException(NullPointerException("body is Null")))
            }
        } else {
            return XSuperResult.Failure(XSuperException(ApiException(response)))
        }
    }

    /**
     * 异常转换为响应结果
     */
    override fun throwableToResult(throwable: Throwable): XSuperResult<Nothing> {
        return XSuperResult.Failure(CodeException(throwable))
    }

    /**
     * 分析结果
     * @param bridge 与组件的通信桥梁
     * @param result 结果
     */
    override fun <T> analysisResult(
        bridge: ComponentActionBridge,
        result: XSuperResult<T>
    ): XSuperResult<T> {
        return if (result.isSuccess()) {
            // 成功的响应，无需额外拦截处理
            result
        } else {
            // 失败的响应，交给异常分析器处理
            val exception = ExceptionAnalyzer.analysisException(
                bridge,
                (result as XSuperResult.Failure).throwable
            )
            if (exception is XSuperException) {
                exception.toFailureResult()
            } else {
                XSuperException(exception).toFailureResult()
            }
        }
    }

}