package com.xxxxls.xsuper.net

import com.xxxxls.xsuper.net.engine.IHttpEngine
import com.xxxxls.xsuper.net.engine.XSuperHttpEngine
import com.xxxxls.xsuper.util.ClassUtils
import com.xxxxls.xsuper.util.L
import okhttp3.OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.lang.reflect.Proxy
import java.util.concurrent.ConcurrentHashMap
import java.util.logging.Logger

/**
 * API 网络请求
 * @author Max
 * @date 2019-11-26.
 */
abstract class ApiRepository<Api : Any> : XSuperRepository(), InvocationHandler {

    //API-Service集合
//    protected val apis: ConcurrentHashMap<Class<*>, Any> by lazy {
//        ConcurrentHashMap<Class<*>, Any>()
//    }

    //网络请求
    protected val mHttpEngine: IHttpEngine by lazy {
        getHttpEngine()
    }

    //    //Api
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

    //API接口（被代理）
    private val apiServiceProxy: Api by lazy {
        Proxy.newProxyInstance(
            javaClass.classLoader,
            arrayOf(*apiService.javaClass.interfaces),
            this
        ) as Api
    }


    /**
     * 获取接口
     */
    fun apis(): Api {
        return apiServiceProxy
    }

    override fun invoke(proxy: Any?, method: Method?, args: Array<out Any>?): Any {
        val annotatedReturnType = method?.genericReturnType
        L.e(message = annotatedReturnType?.typeName)

        val type = method?.returnType
        L.e(message = type?.typeName)
        return method?.invoke(apiService, *args ?: arrayOf()) as Any
    }
}