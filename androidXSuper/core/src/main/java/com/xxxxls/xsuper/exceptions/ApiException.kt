package com.xxxxls.xsuper.exceptions

import com.xxxxls.xsuper.model.SuperResponse

/**
 * 接口请求异常（这里指请求成功，但响应错误）
 * @author Max
 * @date 2019-11-26.
 */
open class ApiException : SuperException {

    // 接口响应体
    var response: SuperResponse<*>? = null

    constructor(response: SuperResponse<*>) : super() {
        this.response = response
    }
}