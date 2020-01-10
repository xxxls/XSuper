package com.xxxxls.utils.date

import java.util.*

/**
 *
 * @author Max
 * @date 2020-01-10.
 */


//<editor-fold desc="Long-转换日期">
/**
 * 转换日期（参数为空，接口也为空）
 */
fun Long?.toDate(): Date? {
    if (this == null) {
        return null
    }
    return Date(this)
}


/**
 * 转换日期不为空（参数为空时默认是当前时间）
 */
fun Long?.toDateNotNull(): Date {
    return Date().apply {
        this@toDateNotNull?.let {
            time = it
        }
    }
}

//</editor-fold>
//<editor-fold desc="String-转换日期">

/**
 * 字段串转换日期
 * @param format 转换格式，默认为yyyy-MM-dd HH:mm:ss
 */
fun String?.toDate(format: String = DateFormat.YMD_HMS.format): Date? {
    if (this == null) {
        return null
    }
    return DateFormat.DIY(format).parseData(this)
}

/**
 * 字段串转换日期
 * @param format 转换格式，默认为yyyy-MM-dd HH:mm:ss
 */
fun String?.toDate(format: DateFormat = DateFormat.YMD_HMS): Date? {
    if (this == null) {
        return null
    }
    return format.parseData(this)
}

//</editor-fold>
//<editor-fold desc="Long-是否是今天">

/**
 * 是否今日
 */
fun Long?.isToDay(): Boolean {
    if (this == null) {
        return false
    }
    return DateUtils.isToDay(this)
}
//</editor-fold>
//<editor-fold desc="Long-是否是今年">

/**
 * 是否今年
 */
fun Long?.isThisYear(): Boolean {
    if (this == null) {
        return false
    }
    return DateUtils.isThisYear(this)
}

//</editor-fold>
//<editor-fold desc="Long-是否是这个月">

/**
 * 是否这个月
 */
fun Long?.isThisMonth(): Boolean {
    if (this == null) {
        return false
    }
    return DateUtils.isThisMonth(this)
}

//</editor-fold>
//<editor-fold desc="Long-添加指定年份">

/**
 * 添加指定年份
 */
fun Long.addYear(add: Int): Long {
    return DateUtils.addYear(add, Date(this)).time
}

//</editor-fold>
//<editor-fold desc="Long-添加指定月份">

/**
 * 添加指定月份
 */
fun Long.addMonth(add: Int): Long {
    return DateUtils.addMonth(add, Date(this)).time
}

//</editor-fold>
//<editor-fold desc="Long-添加指定天">

/**
 * 添加指定天
 */
fun Long.addDay(add: Int): Long {
    return DateUtils.addDay(add, Date(this)).time
}

//</editor-fold>
//<editor-fold desc="Long-添加指定小时">

/**
 * 添加指定小时
 */
fun Long.addHour(add: Int): Long {
    return DateUtils.addHour(add, Date(this)).time
}
//</editor-fold>

