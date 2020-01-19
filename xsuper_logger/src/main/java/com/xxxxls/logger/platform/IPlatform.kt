package com.xxxxls.logger.platform

import com.xxxxls.logger.printer.Printer

/**
 * 平台
 * @author Max
 * @date 2020-01-19.
 */
interface IPlatform {
    /**
     * 行分隔符
     */
    fun getLineSeparator(): String

    /**
     * 默认打印器
     */
    fun getDefaultPrinter(): Printer

    /**
     * 警告
     * @param msg 警告的信息
     */
    fun warn(msg: String)
}