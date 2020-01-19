package com.xxxxls.logger.interceptor

import com.xxxxls.logger.LogEntity
import com.xxxxls.logger.formatter.stacktrace.StackTraceFormatter
import com.xxxxls.logger.util.StackTraceUtil

/**
 * 堆栈跟踪拦截器
 * @author Max
 * @date 2020-01-19.
 */
class StackTraceInterceptor(
    //堆栈深度
    private var stackTraceDepth: Int,
    //格式器
    private val formatter: StackTraceFormatter
) :
    Interceptor {

    override fun onIntercept(logEntity: LogEntity): LogEntity? {

        val depth =
            if (logEntity.getStackTraceDepth() != null) logEntity.getStackTraceDepth()!! else stackTraceDepth

        if (depth > 0) {
            logEntity.stackTraceInfo = formatter.format(
                StackTraceUtil.getCroppedRealStackTrack(
                    Throwable().stackTrace,
                    null,
                    stackTraceDepth
                )
            )
        }
        return logEntity
    }
}