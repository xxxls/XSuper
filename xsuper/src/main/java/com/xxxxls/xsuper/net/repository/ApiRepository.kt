package com.xxxxls.xsuper.net.repository

import com.xxxxls.utils.ClassUtils
import com.xxxxls.utils.L
import com.xxxxls.utils.data.ConcurrentHashMapStore
import com.xxxxls.utils.data.XSuperStore
import com.xxxxls.xsuper.loading.ILoading
import com.xxxxls.xsuper.loading.dismissLoadingInCoroutine
import com.xxxxls.xsuper.loading.showLoadingInCoroutine
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

    // 网络服务存储器
    protected val store: XSuperStore<Class<*>, Any> by lazy {
        ConcurrentHashMapStore<Class<*>, Any>()
    }

    // HTTP-请求器
//    @PublishedApi
    protected val httpEngine: IHttpEngine by lazy {
        createHttpEngine()
    }

    // API-Service-Class
    private var apiClazz: Class<Api>? = null
        get() {
            if (field == null) {
                field = ClassUtils.getSuperClassGenericType<Api>(
                    this::class.java
                )
            }
            return field
        }

    // 默认-API-Service（泛型）
    protected val api: Api by lazy {
        store.get(apiClazz!!) {
            httpEngine.createService(it)
        } as Api
    }

    constructor() : this(null)

    constructor(apiClazz: Class<Api>?) : super() {
        this.apiClazz = apiClazz
    }

    /**
     *  创建API(大多数情况下不需要这样，当需要多API时，需要通过此方法构建)
     */
    protected fun <T> createApi(clazz: Class<T>): T {
        return store.get(key = clazz, build = {
            httpEngine.createService(it)
        }) as T
    }

    /**
     * 获取Http引擎
     * @return 请求实例
     */
    protected abstract fun createHttpEngine(): IHttpEngine


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
        return service(api).enqueue(loading)
    }

    /**
     * 发起请求
     * @param loading 加载组件(null表示不弹窗)
     * @return 结果
     */
    protected suspend fun <T> Deferred<XSuperResponse<T>>.enqueue(
        loading: ILoading? = this@ApiRepository
    ): XSuperResult<T> {
        return withContext(Dispatchers.IO) {
            L.e("enqueue() thread:${Thread.currentThread().name}")
            try {
                loading?.showLoadingInCoroutine(hashCode())
                val response = this@enqueue.await()
                val result = onResponseIntercept(response)
                if (result != null) {
                    return@withContext result
                }
                // 成功响应
                return@withContext XSuperResult.Success<T>(response.getBody())
            } catch (e: Exception) {
                L.e("enqueue() 请求接口异常：$e")
                // 请求过程异常
                return@withContext XSuperResult.Error(httpEngine.requestExceptionConversion(e))
            } finally {
                loading?.dismissLoadingInCoroutine(hashCode())
            }
        }!!
    }

    /**
     * 响应拦截器
     * @param response 接口响应结果
     * @return 拦截后的处理结果
     */
    protected open fun <T> onResponseIntercept(response: XSuperResponse<T>): XSuperResult<T>? {
        httpEngine.getInterceptors()?.forEach { interceptors ->
            val result = interceptors.onIntercept(response, componentBridge)
            if (result != null) {
                return result
            }
        }
        return null
    }

    override fun onCleared() {
        super.onCleared()
        store.clear()
    }

}