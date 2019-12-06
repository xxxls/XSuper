package com.xxxxls.xsuper.net.engine

import com.google.gson.JsonParseException
import com.xxxxls.xsuper.R
import com.xxxxls.xsuper.exceptions.CodeException
import com.xxxxls.xsuper.exceptions.NetWorkException
import com.xxxxls.xsuper.exceptions.XSuperException
import com.xxxxls.xsuper.net.interceptors.IResponseInterceptor
import com.xxxxls.xsuper.util.Utils
import retrofit2.HttpException
import retrofit2.Retrofit
import java.net.ConnectException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

/**
 * 请求引擎
 * @author Max
 * @date 2019-11-26.
 */
open class XSuperHttpEngine(
    private val retrofit: Retrofit,
    private val interceptors: ArrayList<IResponseInterceptor>?
) :
    IHttpEngine {

    override fun getBaseUrl(): String {
        return retrofit.baseUrl().toString()
    }

    override fun getInterceptors(): ArrayList<IResponseInterceptor>? {
        return interceptors
    }

    val httpExceptionMsg = Utils.getApp().getString(R.string.super_network_exception)
    val connectExceptionMsg = Utils.getApp().getString(R.string.super_connect_exception)
    val jsonExceptionMsg = Utils.getApp().getString(R.string.super_parse_json_exception)
    val unknownHostExceptionMsg = Utils.getApp().getString(R.string.super_parse_host_exception)
    val codeExceptionMsg = Utils.getApp().getString(R.string.super_code_exception)

    override fun requestExceptionConversion(throwable: Throwable): XSuperException {

        return when (throwable) {
            is HttpException -> {
                /*网络异常*/
                NetWorkException(httpExceptionMsg)
            }
            is ConnectException,
            is TimeoutException,
            is SocketTimeoutException,
            is SocketException
            -> {
                /*链接异常*/
                NetWorkException(connectExceptionMsg)
            }
            is UnknownHostException -> {
                /*无法解析该域名异常*/
                NetWorkException(unknownHostExceptionMsg)
            }
            is JsonParseException -> {
                /*json解析异常*/
                CodeException(jsonExceptionMsg, throwable)
            }
            else -> {
                /*未知异常*/
                CodeException(codeExceptionMsg, throwable)
            }
        }
    }

    override fun <T> createService(service: Class<T>): T {
        return retrofit.create(service)
    }
}