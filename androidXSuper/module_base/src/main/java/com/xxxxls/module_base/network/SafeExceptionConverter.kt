package com.xxxxls.module_base.network

import android.net.ParseException
import com.google.gson.JsonParseException
import com.xxxxls.module_base.R
import com.xxxxls.utils.AppUtils
import com.xxxxls.xsuper.adapter.ExceptionConverter
import com.xxxxls.xsuper.exceptions.ApiException
import com.xxxxls.xsuper.exceptions.CodeException
import com.xxxxls.xsuper.exceptions.NetWorkException
import org.json.JSONException
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

/**
 * 异常转换器 (友好/安全)
 * @author Max
 * @date 2020/11/30.
 */
class SafeExceptionConverter private constructor() : ExceptionConverter {
    companion object {
        val instance: SafeExceptionConverter by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            SafeExceptionConverter()
        }
    }

    private val httpExceptionMsg = AppUtils.getApp().getString(R.string.base_exception_network)
    private val connectExceptionMsg = AppUtils.getApp().getString(R.string.base_exception_connect)
    private val jsonExceptionMsg = AppUtils.getApp().getString(R.string.base_exception_parse_json)
    private val unknownHostExceptionMsg =
        AppUtils.getApp().getString(R.string.base_exception_parse_host)
    private val codeExceptionMsg = AppUtils.getApp().getString(R.string.base_exception_code)

    override fun convert(throwable: Throwable): Throwable {
        return when (throwable) {
            is ApiException -> {
                /*接口异常*/
                throwable
            }
            is HttpException -> {
                /*网络异常*/
                NetWorkException(httpExceptionMsg, throwable)
            }
            is TimeoutException, is SocketTimeoutException, is ConnectException, is SocketException -> {
                /*网络超时异常*/
                /*链接异常*/
                NetWorkException(connectExceptionMsg, throwable)
            }
            is UnknownHostException -> {
                /*无法解析该域名异常*/
                NetWorkException(unknownHostExceptionMsg, throwable)
            }
            is JsonParseException, is JSONException, is ParseException -> {
                /*json解析异常*/
                CodeException(jsonExceptionMsg, throwable)
            }
            else -> {
                /*其他异常*/
                CodeException(codeExceptionMsg, throwable)
            }
        }
    }

}