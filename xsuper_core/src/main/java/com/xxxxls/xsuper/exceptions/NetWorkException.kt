package com.xxxxls.xsuper.exceptions

/**
 * 网络异常 （网络连续导致的异常）
 * @author Max
 * @date 2019-11-26.
 */
open class NetWorkException : XSuperException {


    constructor(message: String) : super(-1, message)

    constructor(code: Int, message: String) : super(code, message)
    constructor(code: Int, message: String, cause: Throwable?) : super(code, message, cause)
    constructor(
        code: Int,
        message: String,
        cause: Throwable?,
        enableSuppression: Boolean,
        writableStackTrace: Boolean
    ) : super(code, message, cause, enableSuppression, writableStackTrace)

}