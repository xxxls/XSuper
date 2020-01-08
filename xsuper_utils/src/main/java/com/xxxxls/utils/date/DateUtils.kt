package com.xxxxls.utils.date

import java.util.*

/**
 * 日期帮助类
 * @author Max
 * @date 2020-01-08.
 */
object DateUtils {

    init {
        val string = DateFormat.DAY.parseData("")
    }

    /**
     * 获取当前时间
     */
    fun getCurrentTime(format: String): String {
        return DateFormat.DIY(format).formatDate(Date())
    }
}