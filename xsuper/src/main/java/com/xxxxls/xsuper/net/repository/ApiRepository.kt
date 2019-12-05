package com.xxxxls.xsuper.net.repository

import com.xxxxls.xsuper.net.XSuperResponse
import com.xxxxls.xsuper.net.callback.XSuperCallBack
import com.xxxxls.xsuper.net.engine.IHttpEngine
import com.xxxxls.xsuper.util.ClassUtils
import com.xxxxls.xsuper.util.L
import kotlinx.coroutines.*
import java.lang.reflect.ParameterizedType
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import kotlinx.coroutines.Deferred


/**
 * API 网络请求
 * @author Max
 * @date 2019-11-26.
 */
abstract class ApiRepository<Api> : XSuperRepository() {

    //网络请求
    protected val mHttpEngine: IHttpEngine by lazy {
        getHttpEngine()
    }

    //Api
    protected val apiService: Api by lazy {
        mHttpEngine.createService(ClassUtils.getSuperClassGenericType<Api>(javaClass))
    }

    /**
     * 获取Http引擎
     * @return 请求实例
     */
    protected abstract fun getHttpEngine(): IHttpEngine

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
        callBack.showLoading()
        return this@ApiRepository.launch {
            try {
                //响应结果
                val result = this@enqueue.await()
                //响应拦截器走一边
                getHttpEngine().getInterceptors()?.forEach { interceptors ->
                    if (interceptors.onIntercept(result, mComponentBridge, callBack)) {
                        return@launch
                    }
                }

                //默认成功响应
                callBack.onSuccess(result.getBody()!!)
            } catch (e: Exception) {
                L.e("请求接口异常：$e.toString()")
                //请求过程异常
                callBack.onError(getHttpEngine().requestExceptionConversion(e))
            } finally {
                callBack.dismissLoading()
            }
        }
    }

}