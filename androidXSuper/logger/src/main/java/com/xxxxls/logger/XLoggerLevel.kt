package com.xxxxls.logger

/**
 * 日志级别
 * @author Max
 * @date 2020/9/17.
 */
@Deprecated("已废弃")
object XLoggerLevel {

    /**
     * Priority constant for the println method; use Log.v.
     */
    const val VERBOSE = 2

    /**
     * Priority constant for the println method; use Log.d.
     */
    const val DEBUG = 3

    /**
     * Priority constant for the println method; use Log.i.
     */
    const val INFO = 4

    /**
     * Priority constant for the println method; use Log.w.
     */
    const val WARN = 5

    /**
     * Priority constant for the println method; use Log.e.
     */
    const val ERROR = 6

    /**
     * Priority constant for the println method.
     */
    const val ASSERT = 7

    /**
     * Log level for XLog#init, printing all logs.
     */
    const val ALL = Int.MIN_VALUE

    /**
     * Log level for XLog#init, printing no log.
     */
    const val NONE = Int.MAX_VALUE

    /**
     * 自己的日志级别转系统日志级别
     */
    fun toSystemLogLevel(level: Int): Int {
        // 目前级别一致
        if (level > ASSERT) {
            return ASSERT
        }
        if (level < VERBOSE) {
            return VERBOSE
        }
        return level
    }

    /**
     * 自己的日志级别转XLog日志级别
     */
    fun toXLogLevel(level: Int): Int {
        // 目前级别一致
        return level
    }
}