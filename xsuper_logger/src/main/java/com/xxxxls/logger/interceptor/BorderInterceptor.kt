package com.xxxxls.logger.interceptor

import com.xxxxls.logger.LogEntity
import com.xxxxls.logger.formatter.border.BorderFormatter
import com.xxxxls.logger.platform.Platform

/**
 * 边框拦截器
 * @author Max
 * @date 2020-01-19.
 */
class BorderInterceptor(
    //是否展示边框
    private val withBorder: Boolean,
    //边框格式器
    private val formatter: BorderFormatter
) : Interceptor {

    private val lineSeparator: String by lazy {
        Platform.get().getLineSeparator()
    }

    override fun onIntercept(logEntity: LogEntity): LogEntity? {
        val showBorder =
            if (logEntity.withBorder() != null) logEntity.withBorder()!! else withBorder

        val list = ArrayList<String>()

        logEntity.threadInfo?.let {
            list.add(it)
        }
        logEntity.stackTraceInfo?.let {
            list.add(it)
        }
        logEntity.message?.let {
            list.add(it)
        }

        logEntity.message = if (showBorder) {
            formatter.format(
                list.toTypedArray()
            )
        } else {
            list.joinToString(separator = lineSeparator)
        }
        return logEntity
    }

}