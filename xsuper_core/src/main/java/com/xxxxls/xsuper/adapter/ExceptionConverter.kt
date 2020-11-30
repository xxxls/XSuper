package com.xxxxls.xsuper.adapter

/**
 * 异常转换器
 * @author Max
 * @date 2020/11/30.
 */
interface ExceptionConverter {

    /**
     * 转换
     */
    fun convert(throwable: Throwable): Throwable
}