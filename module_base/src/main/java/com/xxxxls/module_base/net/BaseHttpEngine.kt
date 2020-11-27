package com.xxxxls.module_base.net

import com.google.gson.JsonParseException
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.xxxxls.module_base.R
import com.xxxxls.utils.AppUtils
import com.xxxxls.xsuper.exceptions.CodeException
import com.xxxxls.xsuper.exceptions.NetWorkException
import com.xxxxls.xsuper.exceptions.XSuperException
import com.xxxxls.xsuper.network.engine.XSuperHttpEngine
import okhttp3.OkHttpClient
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.ConnectException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

/**
 * http 请求实例（引擎）
 * @author Max
 * @date 2019-12-04.
 */
class BaseHttpEngine(
    retrofit: Retrofit,
    interceptors: ArrayList<IResponseInterceptor>?
) : XSuperHttpEngine(retrofit, interceptors) {

    private val httpExceptionMsg = AppUtils.getApp().getString(R.string.base_exception_network)
    private val connectExceptionMsg = AppUtils.getApp().getString(R.string.base_exception_connect)
    private val jsonExceptionMsg = AppUtils.getApp().getString(R.string.base_exception_parse_json)
    private val unknownHostExceptionMsg =
        AppUtils.getApp().getString(R.string.base_exception_parse_host)
    private val codeExceptionMsg = AppUtils.getApp().getString(R.string.base_exception_code)


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

    class Builder {

        //retrofit
        private var retrofit: Retrofit? = null

        //基础URL
        private var baseUrl: String? = null

        //okhttp客户端
        private var okHttpClient: OkHttpClient? = null

        //响应拦截器
        private val interceptors: ArrayList<IResponseInterceptor> by lazy {
            ArrayList<IResponseInterceptor>()
        }

        /**
         * 设置OkHttpClient
         */
        fun client(okHttpClient: OkHttpClient): Builder {
            this.okHttpClient = okHttpClient
            return this
        }

        /**
         * 设置基础URL
         */
        fun baseUrl(baseUrl: String): Builder {
            this.baseUrl = baseUrl
            return this
        }

        /**
         * 添加响应拦截器
         * @param interceptor 响应拦截器（根据添加时的顺序响应）
         */
        fun interceptor(interceptor: IResponseInterceptor): Builder {
            if (!interceptors.contains(interceptor)) {
                interceptors.add(interceptor)
            }
            return this
        }

        fun build(): BaseHttpEngine {

            checkNotNull(baseUrl) { "baseUrl 不能为空." }
            checkNotNull(okHttpClient) { "okHttpClient 不能为空." }

            retrofit = Retrofit.Builder().baseUrl(baseUrl!!).client(okHttpClient!!)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .build()

            return BaseHttpEngine(retrofit!!, interceptors)
        }
    }

}