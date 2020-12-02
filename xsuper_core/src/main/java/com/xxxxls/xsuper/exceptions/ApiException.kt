package com.xxxxls.xsuper.exceptions

import com.xxxxls.xsuper.model.XSuperResponse

/**
 * 接口请求异常（这里指请求成功，但响应错误）
 * @author Max
 * @date 2019-11-26.
 */
open class ApiException : XSuperException {

    // 接口响应体
    var response: XSuperResponse<*>? = null

    constructor(response: XSuperResponse<*>) : super() {
        this.response = response
    }
}