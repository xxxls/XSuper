package com.xxxxls.xsuper.net.engine

import okhttp3.OkHttpClient

/**
 * 网络请求引擎
 * @author Max
 * @date 2019-11-27.
 */
interface IHttpEngine {

    /**
     * 获取OkHttpClient
     */
    fun getOkHttpClient(): OkHttpClient

    /**
     * 创建请求Service
     */
    fun <T> createService(service: Class<T>): T

}