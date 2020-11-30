package com.xxxxls.xsuper.adapter

import com.xxxxls.xsuper.exceptions.ApiException
import com.xxxxls.xsuper.exceptions.CodeException
import com.xxxxls.xsuper.exceptions.XSuperException
import com.xxxxls.xsuper.model.*
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
     * 接口请求异常转换为响应结果
     */
    override fun throwableToResult(throwable: Throwable): XSuperResult<Nothing> {
        return XSuperResult.Failure(CodeException(throwable))
    }
}