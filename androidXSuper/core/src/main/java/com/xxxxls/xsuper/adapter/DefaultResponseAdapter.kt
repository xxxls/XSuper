package com.xxxxls.xsuper.adapter

import com.xxxxls.xsuper.exceptions.ApiException
import com.xxxxls.xsuper.model.*
import java.lang.NullPointerException

/**
 * 默认响应转换器
 * @author Max
 * @date 2020/11/27.
 */
open class DefaultResponseAdapter : ResponseAdapter {
    /**
     * 获取接口响应体
     */
    override fun <T> getResponseBody(response: SuperResponse<T>): T {
        if (response.isSuccess()) {
            return response.getBody()?.let {
                return@let it
            } ?: let {
                // 这里根据接口规则，可灵活调整
                throw NullPointerException("response body is Null")
            }
        } else {
            // 接口异常
            throw ApiException(response)
        }
    }

    /**
     * 接口响应转换为响应结果保证体
     */
    override fun <T> responseToResult(response: SuperResponse<T>): XSuperResult<T> {
        return try {
            val body = getResponseBody(response)
            XSuperResult.Success(data = body)
        } catch (e: Exception) {
            throwableToResult(e)
        }
    }

    /**
     * 接口请求异常转换为响应结果
     */
    override fun throwableToResult(throwable: Throwable): XSuperResult<Nothing> {
        return XSuperResult.Failure(throwable)
    }
}