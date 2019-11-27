package com.xxxxls.xsuper.net

import com.xxxxls.xsuper.net.engine.IHttpEngine
import com.xxxxls.xsuper.net.engine.XSuperHttpEngine
import com.xxxxls.xsuper.util.ClassUtils
import okhttp3.OkHttpClient
import java.util.concurrent.ConcurrentHashMap

/**
 * API 网络请求
 * @author Max
 * @date 2019-11-26.
 */
abstract class ApiRepository<Api> : XSuperRepository() {

    //API-Service集合
    protected val apis: ConcurrentHashMap<Class<*>, Any> by lazy {
        ConcurrentHashMap<Class<*>, Any>()
    }

    //网络请求
    protected val mHttpEngine: IHttpEngine by lazy {
        getHttpEngine()
    }

    //Api
    protected val apiService: Api by lazy {
        mHttpEngine.createService(ClassUtils.getSuperClassGenericType<Api>(javaClass))
    }

    /**
     * 获取当前Repository 请求的基础URL
     * @return 基础URL
     */
    protected abstract fun getBaseUrl(): String

    /**
     * 获取Http引擎
     */
    protected open fun getHttpEngine(): IHttpEngine {
        //默认构造
        return XSuperHttpEngine.Builder().baseUrl(getBaseUrl())
            .client(OkHttpClient.Builder().build()).build()
    }
}