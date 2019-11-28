package com.xxxxls.module_user.net

import com.xxxxls.module_base.net.BaseApiRepository
import com.xxxxls.xsuper.net.engine.IHttpEngine

/**
 * 用户模块的基础请求Repository (ps:这里只是展示用法，如果某模块的baseUrl或网络公共参数不同可这样单独配置）
 * @author Max
 * @date 2019-11-28.
 */
class BaseUserApiRepository<Api> : BaseApiRepository<Api>() {

    override fun getBaseUrl(): String {
        //可自定义baseUrl
        return super.getBaseUrl()
    }

    override fun getHttpEngine(): IHttpEngine {
        //可自定义网络配置
        return super.getHttpEngine()
    }
}