package com.xxxxls.xsuper.net.repository

import com.xxxxls.xsuper.net.XSuperResponse
import com.xxxxls.xsuper.net.XSuperCallBack
import com.xxxxls.xsuper.net.engine.IHttpEngine
import com.xxxxls.xsuper.net.engine.XSuperHttpEngine
import com.xxxxls.xsuper.util.ClassUtils
import kotlinx.coroutines.*
import okhttp3.OkHttpClient

/**
 * API 网络请求
 * @author Max
 * @date 2019-11-26.
 */
open class ApiRepository<Api> : XSuperRepository() {

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
    protected open fun getBaseUrl(): String {
        return ""
    }

    /**
     * 获取Http引擎
     */
    protected open fun getHttpEngine(): IHttpEngine {
        //默认构造
        return XSuperHttpEngine.Builder().baseUrl(getBaseUrl())
            .client(OkHttpClient.Builder().build()).build()
    }

    /**
     * 获取接口
     */
    fun apis(): Api {
        return apiService
    }

    /**
     * 请求接口
     * @param callBack 结果回调
     * @param service 调用的接口
     */
    fun <T> requestApi(
        callBack: XSuperCallBack<T>,
        service: (it: Api) -> Deferred<XSuperResponse<T>>
    ): Job {
        return service(apiService).enqueue(callBack)
    }

    /**
     * 发起请求
     * @param callBack 结果回调
     */
    protected inline fun <T> Deferred<XSuperResponse<T>>.enqueue(callBack: XSuperCallBack<T>): Job {
        return this@ApiRepository.launch {
            try {
                val result = this@enqueue.await()
                callBack.onSuccess(result.getBody()!!)
            } catch (e: Exception) {
//                callBack.onError(e)
            }
        }
    }

}