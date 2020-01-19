package com.xxxxls.logger.formatter.border

import com.xxxxls.logger.platform.Platform

/**
 * 默认边框格式器
 * <br>╔════════════════════════════════════════════════════════════════════════════
 * <br>║String segment 1
 * <br>╟────────────────────────────────────────────────────────────────────────────
 * <br>║String segment 2
 * <br>╟────────────────────────────────────────────────────────────────────────────
 * <br>║String segment 3
 * <br>╚════════════════════════════════════════════════════════════════════════════
 * @author Max
 * @date 2020-01-19.
 */
class DefaultBorderFormatter : BorderFormatter {

    private val VERTICAL_BORDER_CHAR = '║'

    // Length: 100.
    private val TOP_HORIZONTAL_BORDER =
        "╔═════════════════════════════════════════════════" + "══════════════════════════════════════════════════"

    // Length: 99.
    private val DIVIDER_HORIZONTAL_BORDER =
        "╟─────────────────────────────────────────────────" + "──────────────────────────────────────────────────"

    // Length: 100.
    private val BOTTOM_HORIZONTAL_BORDER =
        "╚═════════════════════════════════════════════════" + "══════════════════════════════════════════════════"

    private val lineSeparator: String by lazy {
        Platform.get().getLineSeparator()
    }

    override fun format(data: Array<String>): String? {
        if (data.isNullOrEmpty()) {
            return ""
        }

        val nonNullSegments = arrayOfNulls<String>(data.size)
        var nonNullCount = 0
        for (segment in data) {
            nonNullSegments[nonNullCount++] = segment
        }
        if (nonNullCount == 0) {
            return ""
        }

        val msgBuilder = StringBuilder()
        msgBuilder.append(TOP_HORIZONTAL_BORDER).append(lineSeparator)
        for (i in 0 until nonNullCount) {
            msgBuilder.append(appendVerticalBorder(nonNullSegments[i] ?: ""))
            if (i != nonNullCount - 1) {
                msgBuilder.append(lineSeparator).append(DIVIDER_HORIZONTAL_BORDER)
                    .append(lineSeparator)
            } else {
                msgBuilder.append(lineSeparator).append(BOTTOM_HORIZONTAL_BORDER)
            }
        }
        return msgBuilder.toString()
    }

    /**
     * 添加垂直边框
     * @param msg 内容
     */
    private fun appendVerticalBorder(msg: String): String {
        val borderedMsgBuilder = StringBuilder(msg.length + 10)
        val lines = msg.split(lineSeparator.toRegex()).dropLastWhile { it.isEmpty() }
            .toTypedArray()
        var i = 0
        val N = lines.size
        while (i < N) {
            if (i != 0) {
                borderedMsgBuilder.append(lineSeparator)
            }
            val line = lines[i]
            borderedMsgBuilder.append(VERTICAL_BORDER_CHAR).append(line)
            i++
        }
        return borderedMsgBuilder.toString()
    }

}