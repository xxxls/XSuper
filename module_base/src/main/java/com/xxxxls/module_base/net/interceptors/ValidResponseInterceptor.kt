package com.xxxxls.module_base.net.interceptors

import com.xxxxls.module_base.net.response.BaseResponse
import com.xxxxls.xsuper.exceptions.ApiException
import com.xxxxls.xsuper.callback.XSuperCallBack
import com.xxxxls.xsuper.model.XSuperResponse
import com.xxxxls.xsuper.component.bridge.IComponentBridge

/**
 * 是否有效请求的拦截器
 * @author Max
 * @date 2019-12-04.
 */
class ValidResponseInterceptor : IResponseInterceptor {
    override fun <T> onIntercept(
        response: XSuperResponse<T>,
        componentBridge: IComponentBridge?,
        callBack: XSuperCallBack<XSuperResponse<T>>
    ): Boolean {
        if (!response.isSuccess()) {
            (response as? BaseResponse<T>)?.apply {
                //响应异常
                callBack.onError(ApiException(this.errorCode ?: 0, this.errorMsg ?: ""))
                return true
            }
        }
        return false
    }
}