package com.xxxxls.logger.interceptor

import com.xxxxls.logger.LogEntity
import com.xxxxls.logger.formatter.thread.ThreadFormatter

/**
 * 线程信息拦截器
 * @author Max
 * @date 2020-01-19.
 */
class ThreadInterceptor(
    //是否携带线程信息
    private var withThread: Boolean,
    //线程信息格式器
    private val threadFormatter: ThreadFormatter
) : Interceptor {
    override fun onIntercept(logEntity: LogEntity): LogEntity? {

        val showThread =
            if (logEntity.withThread() != null) logEntity.withThread()!! else withThread

        if (showThread) {
            logEntity.threadInfo = threadFormatter.format(Thread.currentThread())
        }

        return logEntity
    }
}