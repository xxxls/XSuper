package com.xxxxls.module_base.net

import com.xxxxls.module_base.constants.NetConfig
import com.xxxxls.xsuper.net.repository.ApiRepository
import com.xxxxls.xsuper.net.engine.IHttpEngine
import com.xxxxls.xsuper.net.engine.XSuperHttpEngine
import okhttp3.OkHttpClient

/**
 * 项目基础API
 * @author Max
 * @date 2019-11-27.
 */
open class BaseApiRepository<Api> : ApiRepository<Api>() {
    override fun getBaseUrl(): String {
        return NetConfig.BASE_URL
    }

    override fun getHttpEngine(): IHttpEngine {
        return XSuperHttpEngine.Builder().baseUrl(getBaseUrl())
            .client(OkHttpClient.Builder().build()).build()
    }
}