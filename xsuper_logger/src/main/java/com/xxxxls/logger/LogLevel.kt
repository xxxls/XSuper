package com.xxxxls.logger

/**
 * 日志级别
 * @author Max
 * @date 2020-01-18.
 */
enum class LogLevel(var level: Int) {

    VERBOSE(2),

    DEBUG(3),

    INFO(4),

    WARN(5),

    ERROR(6),

    ALL(Integer.MIN_VALUE),

    NONE(Integer.MAX_VALUE)

}