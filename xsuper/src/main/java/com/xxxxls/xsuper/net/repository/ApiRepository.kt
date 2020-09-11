package com.xxxxls.xsuper.net.repository

import com.xxxxls.utils.ClassUtils
import com.xxxxls.utils.L
import com.xxxxls.xsuper.loading.ILoading
import com.xxxxls.xsuper.net.XSuperResponse
import com.xxxxls.xsuper.net.XSuperResult
import com.xxxxls.xsuper.net.engine.IHttpEngine
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


/**
 * API 网络请求
 * @author Max
 * @date 2019-11-26.
 */
abstract class ApiRepository<Api> : XSuperRepository {

    //网络请求
    protected val mHttpEngine: IHttpEngine by lazy {
        getHttpEngine()
    }

    // API-Service-Class
    private var apiClazz: Class<Api>? = null

    // API-Service
    protected val apiService: Api by lazy {
        mHttpEngine.createService(
            apiClazz ?: ClassUtils.getSuperClassGenericType<Api>(
                this::class.java
            )
        )
    }

    constructor() : this(null)

    constructor(apiClazz: Class<Api>?) : super() {
        this.apiClazz = apiClazz
    }

    /**
     * 获取Http引擎
     * @return 请求实例
     */
    protected abstract fun getHttpEngine(): IHttpEngine

    /**
     * 创建API服务
     */
    fun <Api> apis(clazz: Class<Api>): Api {
        return mHttpEngine.createService(clazz)
    }

    /**
     *
     */
    fun <Api> Class<Api>.api(): Api {
        return mHttpEngine.createService(this)
    }

    /**
     * 获取接口
     */
    fun apis(): Api {
        return apiService
    }

    /**
     * 请求接口
     * @param loading 加载组件
     * @param service 调用的接口
     * @return 结果
     */
    suspend fun <T> requestApi(
        loading: ILoading? = this@ApiRepository,
        service: (it: Api) -> Deferred<XSuperResponse<T>>
    ): XSuperResult<T> {
        return service(apiService).enqueue(loading)
    }

    /**
     * 发起请求
     * @param loading 加载组件
     * @return 结果
     */
    protected suspend fun <T> Deferred<XSuperResponse<T>>.enqueue(
        loading: ILoading? = this@ApiRepository
    ): XSuperResult<T> {
        return withContext(Dispatchers.IO) {
            L.e("enqueue() thread:${Thread.currentThread().name}")
            try {
                loading?.showLoading()
                val response = this@enqueue.await()
                val result = onResponseIntercept(response)
                if (result != null) {
                    return@withContext result
                }
                // 成功响应
                return@withContext XSuperResult.Success<T>(response.getBody())
            } catch (e: Exception) {
                L.e("请求接口异常：$e.toString()")
                // 请求过程异常
                return@withContext XSuperResult.Error(mHttpEngine.requestExceptionConversion(e))
            } finally {
                loading?.dismissLoading()
            }
        }!!
    }

    /**
     * 响应拦截器
     * @param response 接口响应结果
     * @return 拦截后的处理结果
     */
    protected open fun <T> onResponseIntercept(response: XSuperResponse<T>): XSuperResult<T>? {
        mHttpEngine.getInterceptors()?.forEach { interceptors ->
            val result = interceptors.onIntercept(response, mComponentBridge)
            if (result != null) {
                return result
            }
        }
        return null
    }
}