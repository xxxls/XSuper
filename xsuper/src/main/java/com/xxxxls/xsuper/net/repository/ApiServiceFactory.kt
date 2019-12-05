package com.xxxxls.xsuper.net.repository

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * Api接口服务 工厂
 * @author Max
 * @date 2019-11-30.
 */
class ApiServiceFactory<Api>(private val clazz: Class<Api>) :
    ReadOnlyProperty<ApiRepository<*>, Api> {

    private var mApiService: Api? = null

    override fun getValue(thisRef: ApiRepository<*>, property: KProperty<*>): Api {
        if (mApiService == null) {
//            mApiService = thisRef.apis()
        }
        return mApiService!!
    }

}
