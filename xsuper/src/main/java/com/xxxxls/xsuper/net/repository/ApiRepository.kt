package com.xxxxls.xsuper.net.repository

import com.xxxxls.xsuper.net.XSuperResponse
import com.xxxxls.xsuper.net.callback.XSuperCallBack
import com.xxxxls.xsuper.net.engine.IHttpEngine
import com.xxxxls.utils.L
import com.xxxxls.xsuper.exceptions.XSuperException
import com.xxxxls.xsuper.loading.ILoading
import com.xxxxls.xsuper.net.XSuperLiveData
import com.xxxxls.xsuper.net.XSuperResult
import com.xxxxls.xsuper.net.callback.map
import kotlinx.coroutines.*
import kotlinx.coroutines.Deferred
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext


/**
 * API 网络请求
 * @author Max
 * @date 2019-11-26.
 */
abstract class ApiRepository<Api>(var apiClazz: Class<Api>) : XSuperRepository() {

    //网络请求
    protected val mHttpEngine: IHttpEngine by lazy {
        getHttpEngine()
    }

    //Api
    protected val apiService: Api by lazy {
        mHttpEngine.createService(apiClazz)
//        mHttpEngine.createService(ClassUtils.getSuperClassGenericType<Api>(javaClass))
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
     * @return 任务
     */
    fun <T> requestApi(
        callBack: XSuperCallBack<T>,
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        service: (it: Api) -> Deferred<XSuperResponse<T>>
    ): Job {
        return service(apiService).enqueue(callBack = callBack.map {
            return@map it.getBody()!!
        }, context = context, start = start)
    }

    /**
     * 请求接口
     * @param service 调用的接口
     * @return LiveData
     */
    fun <T> requestApi(
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        service: (it: Api) -> Deferred<XSuperResponse<T>>
    ): XSuperLiveData<T> {
        val liveData = XSuperLiveData<T>()
        service(apiService).enqueue(callBack = object : XSuperCallBack<XSuperResponse<T>> {
            override fun onSuccess(result: XSuperResponse<T>) {
                liveData.onSuccess(result = result.getBody()!!)
            }

            override fun onError(exception: XSuperException) {
                liveData.onError(exception)
            }
        }, context = context, start = start)
        return liveData
    }

    /**
     * 请求接口
     * @param service 调用的接口
     * @return 协程
     */
    fun <T> requestApiAsync(
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        service: (it: Api) -> Deferred<XSuperResponse<T>>
    ): Deferred<XSuperResponse<T>> {
        return service(apiService).enqueueAsync(context = context, start = start)
    }


    /**
     * 发起请求
     * @return 任务
     */
    protected suspend fun <T> Deferred<XSuperResponse<T>>.enqueue(
        loading: ILoading? = null,
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT
    ): XSuperResult<T> {
        loading?.showLoading()
        try {
            val response = this@enqueue.await()
            val result = onResponseIntercept(response)
            if (result != null) {
                return result
            }
            // 成功响应
            return XSuperResult.Success<T>(response.getBody())
        } catch (e: Exception) {
            L.e("请求接口异常：$e.toString()")
            // 请求过程异常
            return XSuperResult.Error(mHttpEngine.requestExceptionConversion(e))
        } finally {
            loading?.dismissLoading()
        }
    }

    /**
     * 响应拦截器
     * @param response 接口响应结果
     * @return 拦截后的处理结果
     */
    protected fun <T> onResponseIntercept(response: XSuperResponse<T>): XSuperResult<T>? {
        mHttpEngine.getInterceptors()?.forEach { interceptors ->
            val result = interceptors.onIntercept(response, mComponentBridge)
            if (result != null) {
                return result
            }
        }
        return null
    }

    /**
     * 发起请求
     * @param callBack 结果回调
     * @return 任务
     */
    protected fun <T> Deferred<XSuperResponse<T>>.enqueue(
        callBack: XSuperCallBack<XSuperResponse<T>>,
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT
    ): Job {
        callBack.showLoading()
        return this@ApiRepository.launch(context, start) {
            try {
                //响应结果
                val result = this@enqueue.await()
                //响应拦截器走一边
                mHttpEngine.getInterceptors()?.forEach { interceptors ->
                    if (interceptors.onIntercept(result, mComponentBridge, callBack)) {
                        return@launch
                    }
                }

                //默认成功响应
                callBack.onSuccess(result)
            } catch (e: Exception) {
                L.e("请求接口异常：$e.toString()")
                //请求过程异常
                callBack.onError(mHttpEngine.requestExceptionConversion(e))
            } finally {
                callBack.dismissLoading()
            }
        }
    }

    /**
     * 发起请求
     */
    protected fun <T> Deferred<XSuperResponse<T>>.enqueueAsync(
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT
    ): Deferred<XSuperResponse<T>> {

        return async(context, start) {
            var responseSuccess: XSuperResponse<T>? = null
            var responseError: XSuperException? = null
            val callback = object : XSuperCallBack<XSuperResponse<T>> {
                override fun onSuccess(result: XSuperResponse<T>) {
                    responseSuccess = result
                }

                override fun onError(exception: XSuperException) {
                    responseError = exception
                }
            }
            try {
                //响应结果
                val result = this@enqueueAsync.await()

                //响应拦截器走一边
                mHttpEngine.getInterceptors()?.forEach { interceptors ->
                    if (interceptors.onIntercept(result, mComponentBridge, callback)) {
                        if (responseSuccess != null) {
                            return@async responseSuccess!!
                        } else {
                            throw responseError!!
                        }
                    }
                }
                return@async result
            } catch (e: Exception) {
                L.e("请求接口异常：$e.toString()")
                //请求过程异常
                throw mHttpEngine.requestExceptionConversion(e)
            }
        }
    }


}