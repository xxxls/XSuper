package com.xxxxls.module_base.support

import com.xxxxls.xsuper.exceptions.CodeException
import com.xxxxls.xsuper.exceptions.XSuperException
import com.xxxxls.xsuper.mvvm.ResponseAdapter
import com.xxxxls.xsuper.model.XSuperResponse
import com.xxxxls.xsuper.model.XSuperResult
import java.lang.NullPointerException

/**
 * 接口响应转换器
 * @author Max
 * @date 2020/11/27.
 */
class ApiResponseAdapter : ResponseAdapter {

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
            return XSuperResult.Failure(XSuperException(NullPointerException("body is Null")))
        }
    }

    /**
     * 异常转换为响应结果
     */
    override fun toResult(throwable: Throwable): XSuperResult<Nothing> {
        return XSuperResult.Failure(CodeException(throwable))
    }
}