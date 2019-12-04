package com.xxxxls.xsuper.exceptions

/**
 * 代码处理异常（解析json出错，等）
 * @author Max
 * @date 2019-12-04.
 */
open class CodeException : XSuperException {

    constructor(message: String, cause: Throwable) : super(-1, message, cause)
    constructor(code: Int, message: String, cause: Throwable?) : super(code, message, cause)
    constructor(
        code: Int,
        message: String,
        cause: Throwable?,
        enableSuppression: Boolean,
        writableStackTrace: Boolean
    ) : super(code, message, cause, enableSuppression, writableStackTrace)


}