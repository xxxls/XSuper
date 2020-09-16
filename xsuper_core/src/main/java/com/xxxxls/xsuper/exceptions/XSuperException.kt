package com.xxxxls.xsuper.exceptions


/**
 * Super - Exception
 * @author Max
 * @date 2019-11-26.
 */
open class XSuperException : Exception {

    /**
     * 错误码
     */
    var code: Int = 0

    /**
     * 显示的异常信息
     */
    var displayMessage: String

    constructor(code: Int = 0, message: String = "") : super(message) {
        this.code = code
        this.displayMessage = message
    }

    constructor(code: Int = 0, message: String = "", cause: Throwable?) : super(message, cause) {
        this.code = code
        this.displayMessage = message
    }

    constructor(
        code: Int = 0,
        message: String = "",
        cause: Throwable?,
        enableSuppression: Boolean,
        writableStackTrace: Boolean
    ) : super(message, cause, enableSuppression, writableStackTrace) {
        this.code = code
        this.displayMessage = message
    }

}