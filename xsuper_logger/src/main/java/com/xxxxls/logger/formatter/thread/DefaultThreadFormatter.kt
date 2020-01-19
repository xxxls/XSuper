package com.xxxxls.logger.formatter.thread

/**
 * 默认线程格式器
 * @author Max
 * @date 2020-01-19.
 */
class DefaultThreadFormatter : ThreadFormatter {
    override fun format(data: Thread): String? {
        return "Thread: " + data.name
    }
}