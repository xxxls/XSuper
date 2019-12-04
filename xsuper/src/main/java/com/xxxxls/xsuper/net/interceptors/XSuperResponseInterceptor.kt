package com.xxxxls.xsuper.net.interceptors

import com.xxxxls.xsuper.net.XSuperCallBack
import com.xxxxls.xsuper.net.XSuperResponse

/**
 * super - 接口响应拦截器
 * @author Max
 * @date 2019-12-04.
 */
interface XSuperResponseInterceptor {

    /**
     * 拦截触发
     * @return 是否拦截（拦截后不再走默认的响应，需自己响应）
     */
    fun <T> onIntercept(response: XSuperResponse<T>, callBack: XSuperCallBack<T>): Boolean {
        return false
    }
}