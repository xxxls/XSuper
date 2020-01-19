package com.xxxxls.logger.formatter

/**
 * 格式化
 * @author Max
 * @date 2020-01-19.
 */
interface Formatter<T> {

    /**
     * 格式化字符串
     */
    fun format(data: T): String?
}