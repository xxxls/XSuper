package com.xxxxls.xsuper.network.engine

import com.xxxxls.xsuper.exceptions.XSuperException

/**
 * 网络请求引擎
 * @author Max
 * @date 2019-11-27.
 */
interface IHttpEngine {

    /**
     * 获取请求地址
     */
    fun getBaseUrl(): String

    /**
     * 创建请求Service
     */
    fun <T> createService(service: Class<T>): T

    /**
     * 请求异常转换
     * @param throwable 异常信息
     * @return 转换为可读异常
     */
    fun requestExceptionConversion(throwable: Throwable): XSuperException
}