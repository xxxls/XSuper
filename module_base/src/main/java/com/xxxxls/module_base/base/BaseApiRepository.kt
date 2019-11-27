package com.xxxxls.module_base.base

import com.xxxxls.xsuper.net.ApiRepository
import com.xxxxls.xsuper.net.engine.IHttpEngine

/**
 *
 * @author Max
 * @date 2019-11-27.
 */
open class BaseApiRepository<Api> : ApiRepository<Api>() {
    override fun getBaseUrl(): String {
        return "xxxxx"
    }

    override fun getHttpEngine(): IHttpEngine {
        return super.getHttpEngine()
    }
}