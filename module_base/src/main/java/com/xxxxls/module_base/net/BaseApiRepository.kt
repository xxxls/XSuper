package com.xxxxls.module_base.net

import com.xxxxls.module_base.constants.NetConfig
import com.xxxxls.module_base.net.interceptors.ValidResponseInterceptor
import com.xxxxls.xsuper.net.repository.ApiRepository
import com.xxxxls.xsuper.net.engine.IHttpEngine
import com.xxxxls.xsuper.net.interceptors.LoggerInterceptor
import okhttp3.OkHttpClient

/**
 * 项目基础API
 * @author Max
 * @date 2019-11-27.
 */
open class BaseApiRepository<Api> : ApiRepository<Api> {

    constructor() : super()
    constructor(apiClazz: Class<Api>?) : super(apiClazz)

    override fun getHttpEngine(): IHttpEngine {
        return HttpEngineManager.getInstance().buildEngine(NetConfig.BASE_URL) {
            BaseHttpEngine.Builder().baseUrl(NetConfig.BASE_URL)
                .interceptor(ValidResponseInterceptor())
                .client(OkHttpClient.Builder().addInterceptor(LoggerInterceptor()).build()).build()
        }
    }
}