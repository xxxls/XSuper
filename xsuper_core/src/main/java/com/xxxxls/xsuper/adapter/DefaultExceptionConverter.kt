package com.xxxxls.xsuper.adapter

/**
 * 默认异常转换
 * @author Max
 * @date 2020/11/30.
 */
class DefaultExceptionConverter : ExceptionConverter {
    override fun convert(throwable: Throwable): Throwable {
        return throwable
    }
}