package com.xxxxls.module_base.net

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.xxxxls.xsuper.net.engine.XSuperHttpEngine
import com.xxxxls.xsuper.net.interceptors.XSuperResponseInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * http 请求实例（引擎）
 * @author Max
 * @date 2019-12-04.
 */
class BaseHttpEngine(
    retrofit: Retrofit,
    interceptors: ArrayList<XSuperResponseInterceptor>?
) : XSuperHttpEngine(retrofit, interceptors) {

    class Builder {

        //retrofit
        private var retrofit: Retrofit? = null

        //基础URL
        private var baseUrl: String? = null

        //okhttp客户端
        private var okHttpClient: OkHttpClient? = null

        //响应拦截器
        private val interceptors: ArrayList<XSuperResponseInterceptor> by lazy {
            ArrayList<XSuperResponseInterceptor>()
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
        fun interceptor(interceptor: XSuperResponseInterceptor): Builder {
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