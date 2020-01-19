package com.xxxxls.logger.formatter.stacktrace

import com.xxxxls.logger.platform.Platform

/**
 * 默认堆栈跟踪格式器
 * @author Max
 * @date 2020-01-19.
 */
class DefaultStackTraceFormatter : StackTraceFormatter {
    override fun format(stackTrace: Array<StackTraceElement>): String? {
        val sb = StringBuilder(256)
        when {
            stackTrace.isNullOrEmpty() -> return null
            stackTrace.size == 1 -> return "\t─ " + stackTrace[0].toString()
            else -> {
                var i = 0
                val N = stackTrace.size
                while (i < N) {
                    if (i != N - 1) {
                        sb.append("\t├ ")
                        sb.append(stackTrace[i].toString())
                        sb.append(Platform.get().getLineSeparator())
                    } else {
                        sb.append("\t└ ")
                        sb.append(stackTrace[i].toString())
                    }
                    i++
                }
                return sb.toString()
            }
        }
    }
}