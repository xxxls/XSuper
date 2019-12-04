package com.xxxxls.xsuper.net.viewmodel

import com.xxxxls.xsuper.net.XSuperResponse
import com.xxxxls.xsuper.net.XSuperCallBack
import com.xxxxls.xsuper.net.repository.ApiRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job

/**
 * ViewModel - 快速接口
 * @author Max
 * @date 2019-12-02.
 */
open class FastApiViewModel<Api> : XSuperViewModel() {

    // 快速Api
    val mRepositoryApi = createRepository<ApiRepository<Api>>()

    /**
     * 请求接口
     * @param callBack 结果回调
     * @param service 调用的接口
     */
    fun <T> requestApi(
        callBack: XSuperCallBack<T>,
        service: (it: Api) -> Deferred<XSuperResponse<T>>
    ): Job {
        return mRepositoryApi.requestApi(callBack, service)
    }

}
