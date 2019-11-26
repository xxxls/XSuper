package com.xxxxls.xsuper.net

/**
 * API 请求
 * @author Max
 * @date 2019-11-26.
 */
abstract class ApiRepository : XSuperRepository() {

    /**
     * 获取当前Repository 请求的基础URL
     * @return 基础URL
     */
    protected abstract fun getBaseUrl(): String


}