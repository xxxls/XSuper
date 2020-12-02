package com.xxxxls.xsuper.net.interceptors

import com.xxxxls.xsuper.net.callback.XSuperCallBack
import com.xxxxls.xsuper.net.XSuperResponse
import com.xxxxls.xsuper.net.XSuperResult
import com.xxxxls.xsuper.net.bridge.IComponentBridge

/**
 * 接口响应拦截器
 * @author Max
 * @date 2019-12-04.
 */
interface IResponseInterceptor {

    /**
     * 拦截触发
     * @param response 接口响应体
     * @param componentBridge 组件通信桥
     * @param callBack 当前请求的回调
     * @return 是否拦截（拦截后不再走默认的响应，需自己响应）
     */
    fun <T> onIntercept(
        response: XSuperResponse<T>,
        componentBridge: IComponentBridge?,
        callBack: XSuperCallBack<XSuperResponse<T>>
    ): Boolean


    /**
     * 拦截触发
     * @param response 响应体
     * @param componentBridge 组件通信桥
     * @return 拦截并处理结果 （不为空表示拦截此请求，并把返回结果响应出去）
     */
    fun <T> onIntercept(
        response: XSuperResponse<T>,
        componentBridge: IComponentBridge?
    ): XSuperResult<T>? {
        return null
    }
}