package com.xxxxls.xsuper.net.engine

import com.xxxxls.xsuper.exceptions.XSuperException
import com.xxxxls.xsuper.net.interceptors.XSuperResponseInterceptor
import okhttp3.OkHttpClient
import kotlin.Exception

/**
 * 网络请求引擎
 * @author Max
 * @date 2019-11-27.
 */
interface IHttpEngine {

    /**
     * 创建请求Service
     */
    fun <T> createService(service: Class<T>): T

    /**
     * 获取响应拦截器
     * @return 响应拦截器（对请求的数据进行全局拦截操作，如：登录失效，版本更新等）
     */
    fun getInterceptors(): ArrayList<XSuperResponseInterceptor>?

    /**
     * 请求异常转换
     * @param throwable 异常信息
     * @return 转换为可读异常
     */
    fun requestExceptionConversion(throwable: Throwable): XSuperException
}