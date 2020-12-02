package com.xxxxls.xsuper.exceptions

/**
 * 网络异常 （网络连续导致的异常）
 * @author Max
 * @date 2019-11-26.
 */
open class NetWorkException : XSuperException {

    constructor(message: String, code: Int = 0) : super(message, code)
    constructor(message: String, cause: Throwable?, code: Int = 0) : super(message, cause, code)
}