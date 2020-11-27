package com.xxxxls.xsuper.exceptions

import com.xxxxls.xsuper.model.XSuperResponse

/**
 * 接口请求异常（这里指请求成功，但响应错误）
 * @author Max
 * @date 2019-11-26.
 */
open class ApiException : XSuperException {

    var response: XSuperResponse<*>? = null

    constructor(response: XSuperResponse<*>?) : super() {
        this.response = response
    }

    constructor(code: Int, message: String) : super(code, message)
    constructor(code: Int, message: String, cause: Throwable?) : super(code, message, cause)
    constructor(
        code: Int,
        message: String,
        cause: Throwable?,
        enableSuppression: Boolean,
        writableStackTrace: Boolean
    ) : super(code, message, cause, enableSuppression, writableStackTrace)

    constructor(cause: Throwable?) : super(cause)
}