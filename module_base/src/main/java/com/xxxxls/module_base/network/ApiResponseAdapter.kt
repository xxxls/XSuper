package com.xxxxls.module_base.network

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
class ApiResponseAdapter private constructor() : ResponseAdapter {

    companion object{

        val instance: ApiResponseAdapter by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            ApiResponseAdapter()
        }
    }

    /**
     * 接口响应转换为响应结果
     * (根据状态码等区分出成功与失败)
     */
    override fun <T> responseToResult(response: XSuperResponse<T>): XSuperResult<T> {
        if (response.isSuccess()) {
            response.getBody()?.let {
                return XSuperResult.Success<T>(data = it)
            } ?: let {
                return XSuperResult.Failure(XSuperException(NullPointerException("response body is Null")))
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

}