package com.xxxxls.module_base.net

import com.xxxxls.utils.ClassUtils
import com.xxxxls.xsuper.net.XSuperResponse
import com.xxxxls.xsuper.net.XSuperResult
import com.xxxxls.xsuper.net.callback
import com.xxxxls.xsuper.net.callback.XSuperCallBack
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job

/**
 * ViewModel - 快速接口
 * @author Max
 * @date 2019-12-02.
 */
open class FastApiViewModel<Api> : BaseViewModel() {

    // 快速Api
    private val repositoryApi: BaseApiRepository<Api> by lazy {
        val clazz = ClassUtils.getSuperClassGenericType<Api>(this.javaClass)
        val repository = BaseApiRepository<Api>(clazz)
        addRepository(repository)
    }

    /**
     * 请求接口
     * @param service 调用的接口
     * @return 结果
     */
    suspend fun <T> requestApi(
        service: (it: Api) -> Deferred<XSuperResponse<T>>
    ): XSuperResult<T> {
        return repositoryApi.requestApi(service = service)
    }

    /**
     * 请求接口
     * @param callBack 结果回调
     * @param service 调用的接口
     * @return 任务
     */
    fun <T> requestApi(
        callBack: XSuperCallBack<T>,
        service: (it: Api) -> Deferred<XSuperResponse<T>>
    ): Job {
        return launch {
            repositoryApi.requestApi(service = service).callback(callBack)
        }
    }
}
