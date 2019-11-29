package com.xxxxls.xsuper.net.engine

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * 请求引擎
 * @author Max
 * @date 2019-11-26.
 */
class XSuperHttpEngine private constructor(val builder: Builder) : IHttpEngine {


    override fun getOkHttpClient(): OkHttpClient {
        return builder.okHttpClient!!
    }

    override fun <T> createService(service: Class<T>): T {
        return builder.retrofit!!.create(service)
    }

    class Builder {

        internal var retrofit: Retrofit? = null

        private var baseUrl: String? = null

        internal var okHttpClient: OkHttpClient? = null

        fun client(okHttpClient: OkHttpClient): Builder {
            this.okHttpClient = okHttpClient
            return this
        }

        fun baseUrl(baseUrl: String): Builder {
            this.baseUrl = baseUrl
            return this
        }

        fun build(): XSuperHttpEngine {

            checkNotNull(baseUrl) { "Base URL required." }

            checkNotNull(okHttpClient) { "Base URL required." }

            retrofit = Retrofit.Builder().baseUrl(baseUrl!!).client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()

            return XSuperHttpEngine(this)
        }
    }

}