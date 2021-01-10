package com.xxxxls.utils.date

import java.text.SimpleDateFormat
import java.util.*

/**
 * 日期格式
 * @author Max
 * @date 2020-01-08.
 */
sealed class DateFormat(val format: String) {

    //dateFormat
    private val dateFormat: SimpleDateFormat by lazy {
        SimpleDateFormat(format)
    }

    /**
     * SimpleDateFormat
     * 在此请注意几个字母大小写的差异:
     *
     * 大写的H为24小时制表示一天中的小时数(0-23)
     * 小写的h为12小时制表示一天中的小时数(1-12)
     *
     * 大写的M表示年中的月份
     * 小写的m表示小时中的分钟数
     *
     * 大写的S表示毫秒数
     * 小写的s表示秒数
     *
     * 所以最常用的24小时制的具体日期的pattern为:
     * yyyy-MM-dd HH:mm:ss
     *
     * SimpleDateFormat中format()方法小结:
     * format()方法的作用是将日期(Date)转换为文本
     *
     * SimpleDateFormat中parse()方法小结:
     * parse()方法的作用是将文本转换为日期(Date)
     */

    //获取SimpleDateFormat
    private fun getSimpleDateFormat(): SimpleDateFormat {
        return dateFormat
    }

    /**
     * 日期(Date)转换为文本(参数为空时默认当前日期)
     * @param date
     */
    fun formatDate(date: Date?): String {
        return dateFormat.format(date ?: Date())
    }


    /**
     * 日期(Date)转换为文本(可能为null)
     * @param date
     */
    fun formatDateOrNull(date: Date?): String? {
        if (date == null) {
            return null
        }
        return dateFormat.format(date)
    }

    /**
     * 文本转换为日期(Date)
     */
    fun parseData(source: String): Date? {
        return try {
            dateFormat.parse(source)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    //年
    object YEAR : DateFormat("yyyy")

    //月
    object MONTH : DateFormat("MM")

    //日
    object DAY : DateFormat("dd")

    //小时（24小时制）
    object HOUR : DateFormat("HH")

    //分钟
    object MINUTE : DateFormat("mm")

    //秒
    object SECONDES : DateFormat("ss")

    //毫秒
    object MILLISECOND : DateFormat("SSS")

    //年-月-日
    object YMD : DateFormat("yyyy-MM-dd")

    //时:分 (24小时制)
    object HM : DateFormat("HH:mm")

    //时:分 (12小时制)
    object hm_12 : DateFormat("hh:mm")

    //时:分:秒 (24小时制)
    object HMS : DateFormat("HH:mm:ss")

    //时:分:秒(12小时制)
    object hms_12 : DateFormat("hh:mm:ss")

    //分:秒
    object ms : DateFormat("mm:ss")

    //年-月-日 时:分:秒(24小时制)
    object YMD_HMS : DateFormat("yyyy-MM-dd HH:mm:ss")

    //年-月-日 时:分:秒(12小时制)
    object YMD_hms_12 : DateFormat("yyyy-MM-dd hh:mm:ss")

    //年-月-日 时:分:秒|毫秒(24小时制)
    object YMD_HMS_SS : DateFormat("yyyy-MM-dd HH:mm:ss|SS")

    //年-月-日 时:分:秒|毫秒(12小时制)
    object YMD_hms_SS_12 : DateFormat("yyyy-MM-dd HH:mm:ss|SS")

    //年-月-日 时:分:秒:毫秒(24小时制)
    object YMD_HMS_SSS : DateFormat("yyyy-MM-dd HH:mm:ss|SSS")

    //年-月-日 时:分:秒|毫秒(12小时制)
    object YMD_hms_SSS_12 : DateFormat("yyyy-MM-dd hh:mm:ss|SSS")

    //自定义格式
    class DIY(format: String) : DateFormat(format)
}
