package com.xxxxls.xsuper.network.engine

import com.xxxxls.xsuper.exceptions.XSuperException
import retrofit2.Retrofit

/**
 * 请求引擎
 * @author Max
 * @date 2019-11-26.
 */
open class XSuperHttpEngine(
    private val retrofit: Retrofit
) :
    IHttpEngine {

    override fun getBaseUrl(): String {
        return retrofit.baseUrl().toString()
    }

    /**
     * 网络异常转换
     * @param throwable 原始异常
     * @return 转换后的异常信息
     */
    override fun requestExceptionConversion(throwable: Throwable): XSuperException {
        return XSuperException(
            code = 0,
            message = throwable.message ?: throwable.toString(),
            cause = throwable
        )
    }

    override fun <T> createService(service: Class<T>): T {
        return retrofit.create(service)
    }
}