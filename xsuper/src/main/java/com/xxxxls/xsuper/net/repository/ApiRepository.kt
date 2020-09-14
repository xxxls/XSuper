package com.xxxxls.xsuper.net.repository

import com.xxxxls.utils.ClassUtils
import com.xxxxls.utils.L
import com.xxxxls.xsuper.clazz.ClazzProvider
import com.xxxxls.xsuper.clazz.DefaultApiFactory
import com.xxxxls.xsuper.clazz.MemoryStore
import com.xxxxls.xsuper.loading.ILoading
import com.xxxxls.xsuper.loading.dismissLoadingInCoroutine
import com.xxxxls.xsuper.loading.showLoadingInCoroutine
import com.xxxxls.xsuper.net.XSuperResponse
import com.xxxxls.xsuper.net.XSuperResult
import com.xxxxls.xsuper.net.engine.IHttpEngine
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.concurrent.ConcurrentHashMap

/**
 * API 网络请求
 * @author Max
 * @date 2019-11-26.
 */
abstract class ApiRepository<Api> : XSuperRepository {

    // HTTP-请求器
    protected val httpEngine: IHttpEngine by lazy {
        createHttpEngine()
    }

    // API-Service-Class
    protected var apiClazz: Class<Api>? = null
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
        apiProvider.get(apiClazz as Class<Any>) as Api
    }

    // API - 提供者
    protected val apiProvider: ClazzProvider by lazy {
        createApiProvider()
    }

    // API - 存储器
    protected val apiStore: ClazzProvider.Store by lazy {
        createApiStore()
    }

    constructor() : this(null)

    constructor(apiClazz: Class<Api>?) : super() {
        this.apiClazz = apiClazz
    }

    /**
     * 获取Http引擎
     * @return 请求实例
     */
    protected abstract fun createHttpEngine(): IHttpEngine

    /**
     * 创建API提供
     */
    protected open fun createApiProvider(): ClazzProvider {
        return ClazzProvider(
            apiStore,
            DefaultApiFactory(httpEngine)
        )
    }

    /**
     * API 存储器
     */
    protected open fun createApiStore(): ClazzProvider.Store {
        return MemoryStore()
    }

    /**
     * 创建API(大多数情况下不需要这样，当需要多API时，需要通过此方法构建)
     */
    inline fun <reified Api> api(): Api {
        return apiProvider.get(Api::class.java)
    }

    /**
     * 创建API服务
     */
    fun <Api> api(clazz: Class<Api>): Api {
        return apiProvider.get(clazz = clazz as Class<Any>) as Api
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
        apiStore.clear()
    }

}