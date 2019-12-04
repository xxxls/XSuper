package com.xxxxls.module_base.net

import com.xxxxls.module_base.constants.NetConfig
import com.xxxxls.module_base.net.interceptors.ValidResponseInterceptor
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

    override fun getHttpEngine(): IHttpEngine {
        //TODO 同样的引擎应注意避免重复创建，这里只是演示
        return BaseHttpEngine.Builder().baseUrl(NetConfig.BASE_URL)
            .interceptor(ValidResponseInterceptor())
            .client(OkHttpClient.Builder().build()).build()
    }
}