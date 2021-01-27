package com.xxxxls.xsuper.exceptions


/**
 * Super - Exception
 * @author Max
 * @date 2019-11-26.
 */
open class SuperException : Exception {

    /**
     * 错误码
     */
    var code: Int = 0

    constructor(code: Int = 0) : super() {
        this.code = code
    }

    constructor(message: String?, code: Int = 0) : super(message) {
        this.code = code
    }

    constructor(message: String?, cause: Throwable?, code: Int = 0) : super(message, cause) {
        this.code = code
    }

    constructor(cause: Throwable?, code: Int = 0) : super(cause) {
        this.code = code
    }

    override fun toString(): String {
        return "[code=$code, message=$message, cause=$cause]"
    }
}