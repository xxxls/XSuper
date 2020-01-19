package com.xxxxls.logger.printer

import com.xxxxls.logger.LogLevel

/**
 * Android打印
 * @author Max
 * @date 2020-01-19.
 */
class AndroidPrinter(private val maxChunkSize: Int = DEFAULT_MAX_CHUNK_SIZE) : Printer {

    companion object {
        /**
         * Generally, android has a default length limit of 4096 for single log, but
         * some device(like HUAWEI) has its own shorter limit, so we just use 2048
         * and wish it could run well in all devices.
         */
        internal const val DEFAULT_MAX_CHUNK_SIZE = 2048
    }

    override fun println(logLevel: LogLevel, tag: String, msg: String) {
        if (msg.length <= maxChunkSize) {
            printChunk(logLevel.level, tag, msg)
            return
        }

        val msgLength = msg.length
        var start = 0
        var end: Int
        while (start < msgLength) {
            end = adjustEnd(msg, start, (start + maxChunkSize).coerceAtMost(msgLength))
            printChunk(logLevel.level, tag, msg.substring(start, end))

            start = end
        }
    }

    /**
     * Move the end to the nearest line separator('\n') (if exist).
     */
    private fun adjustEnd(msg: String, start: Int, originEnd: Int): Int {
        if (originEnd == msg.length) {
            // Already end of message.
            return originEnd
        }
        if (msg[originEnd] == '\n') {
            // Already prior to '\n'.
            return originEnd
        }
        // Search back for '\n'.
        var last = originEnd - 1
        while (start < last) {
            if (msg[last] == '\n') {
                return last + 1
            }
            last--
        }
        return originEnd
    }

    /**
     * Print single chunk of log in new line.
     *
     * @param logLevel the level of log
     * @param tag      the tag of log
     * @param msg      the msg of log
     */
    private fun printChunk(logLevel: Int, tag: String, msg: String) {
        android.util.Log.println(logLevel, tag, msg)
    }
}