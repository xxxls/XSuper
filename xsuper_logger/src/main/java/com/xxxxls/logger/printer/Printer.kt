package com.xxxxls.logger.printer

import com.xxxxls.logger.LogLevel

/**
 * 打印器
 * @author Max
 * @date 2020-01-18.
 */
interface Printer {

    /**
     * 打印日志
     * @param logLevel 日志级别
     * @param tag 日志标记
     * @param msg 打印的信息
     */
    fun println(logLevel: LogLevel, tag: String, msg: String)
}