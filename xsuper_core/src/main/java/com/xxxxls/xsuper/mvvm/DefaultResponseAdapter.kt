package com.xxxxls.xsuper.mvvm

import com.xxxxls.xsuper.exceptions.ApiException
import com.xxxxls.xsuper.exceptions.CodeException
import com.xxxxls.xsuper.exceptions.XSuperException
import com.xxxxls.xsuper.model.XSuperResponse
import com.xxxxls.xsuper.model.XSuperResult
import com.xxxxls.xsuper.model.isSuccess
import java.lang.NullPointerException

/**
 * 默认响应转换器
 * @author Max
 * @date 2020/11/27.
 */
open class DefaultResponseAdapter : ResponseAdapter {

    /**
     * 接口响应转换为响应结果
     */
    override fun <T> toResult(response: XSuperResponse<T>): XSuperResult<T> {
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
    override fun toResult(throwable: Throwable): XSuperResult<Nothing> {
        return XSuperResult.Failure(CodeException(throwable))
    }


    /**
     * 响应结果统一拦截处理（异常统一处理）
     */
    private fun <T> onResultIntercept(result: XSuperResult<T>): XSuperResult<T> {
        if (result.isSuccess()) {

        } else {

        }
        return result
    }
}