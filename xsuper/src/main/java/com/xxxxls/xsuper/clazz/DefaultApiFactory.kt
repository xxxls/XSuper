package com.xxxxls.xsuper.clazz

import com.xxxxls.xsuper.net.engine.IHttpEngine

/**
 * 默认API工厂
 * @author Max
 * @date 2020/9/14.
 */
class DefaultApiFactory(private val httpEngine: IHttpEngine) : ClazzProvider.Factory {

    override fun <T> create(clazz: Class<T>): T {
        return httpEngine.createService(clazz)
    }
}