package com.xxxxls.logger

/**
 * 日志实体
 * @author Max
 * @date 2020-01-19.
 */
class LogEntity(
    //日志级别
    var logLevel: LogLevel,
    //日志标签
    var tag: String,
    //日志内容
    var message: String? = null,
    //线程信息
    var threadInfo: String? = null,
    //堆栈跟踪信息
    var stackTraceInfo: String? = null,
    //是否展示边框
    var withBorder: Boolean? = null,
    //是否携带线程信息
    var withThread: Boolean? = null,
    //堆栈信息深度
    var depth: Int? = null
) : LogParams {

    /**
     * 是否携带边框
     */
    override fun withBorder(): Boolean? {
        return withBorder
    }

    /**
     * 是否携带线程信息
     */
    override fun withThread(): Boolean? {
        return withThread
    }

    /**
     * 堆栈信息深度
     */
    override fun getStackTraceDepth(): Int? {
        return depth
    }

    /**
     * 获取消息(非空)
     */
    fun getMessageNotNull(): String {
        return message ?: ""
    }
}