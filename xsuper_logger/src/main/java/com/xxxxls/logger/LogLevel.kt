package com.xxxxls.logger

/**
 * 日志级别
 * @author Max
 * @date 2020-01-18.
 */
enum class LogLevel(var level: Int, var levelName: String, var shortName: String) {

    VERBOSE(2, "VERBOSE", "V"),

    DEBUG(3, "DEBUG", "D"),

    INFO(4, "INFO", "I"),

    WARN(5, "WARN", "W"),

    ERROR(6, "ERROR", "E"),

    ALL(Integer.MIN_VALUE, "ALL", "A"),

    NONE(Integer.MAX_VALUE, "NONE", "N")

}