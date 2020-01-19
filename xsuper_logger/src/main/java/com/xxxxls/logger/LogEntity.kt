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
    var stackTraceInfo: String? = null
) {

    /**
     * 获取消息(非空)
     */
    fun getMessageNotNull(): String {
        return message ?: ""
    }
}