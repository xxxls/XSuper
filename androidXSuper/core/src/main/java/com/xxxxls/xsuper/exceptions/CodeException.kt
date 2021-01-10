package com.xxxxls.xsuper.exceptions

/**
 * 代码处理异常（解析json出错，等）
 * @author Max
 * @date 2019-12-04.
 */
open class CodeException : XSuperException {
    constructor(code: Int = 0) : super(code)
    constructor(message: String?, code: Int = 0) : super(message, code)
    constructor(message: String?, cause: Throwable?, code: Int = 0) : super(message, cause, code)
    constructor(cause: Throwable?, code: Int = 0) : super(cause, code)
}