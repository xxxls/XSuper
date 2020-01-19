package com.xxxxls.logger

import com.xxxxls.logger.printer.AndroidPrinter
import com.xxxxls.logger.printer.IPrinter

/**
 * LOGGER
 * @author Max
 * @date 2020-01-18.
 */
object XSuperLogger {

    /**
     * 日志配置
     */
    private var configuration: LogConfiguration? = null

    /**
     * 打印器
     */
    private var printer: IPrinter = AndroidPrinter()

    /**
     * 初始化
     */
    fun init(configuration: LogConfiguration) {
        this.configuration = configuration
    }

    /**
     * 设置打印器
     */
    fun setPrinter(iPrinter: IPrinter) {
        this.printer = iPrinter
    }

    /**
     * 获取配置
     */
    private fun getConfiguration(): LogConfiguration {
        if (configuration == null) {
            synchronized(this) {
                if (configuration == null) {
                    configuration = LogConfiguration.newBuilder().build()
                }
            }
        }
        return configuration!!
    }

    /**
     * log-D
     */
    fun d(message: String, tag: String = getConfiguration().tag) {
        println(logLevel = LogLevel.DEBUG, tag = tag, message = message)
    }

    /**
     * log-E
     */
    fun e(message: String, tag: String = getConfiguration().tag) {
        println(logLevel = LogLevel.ERROR, tag = tag, message = message)
    }

    /**
     * 打印日志
     * @param logLevel 级别
     * @param tag 标签
     * @param message 内容
     */
    private fun println(logLevel: LogLevel, tag: String, message: String) {
        var logEntity: LogEntity? = LogEntity(logLevel = logLevel, tag = tag, message = message)
        getConfiguration().interceptors?.forEach {
            logEntity = it.onIntercept(logEntity!!)
            if (logEntity == null) {
                return
            }
        }

        logEntity?.run {
            println(this)
        }
    }

    /**
     * 打印日志
     * @param logEntity 日志实体
     */
    private fun println(logEntity: LogEntity) {
        printer.println(
            logLevel = logEntity.logLevel,
            tag = logEntity.tag,
            msg = logEntity.getMessageNotNull()
        )
    }
}