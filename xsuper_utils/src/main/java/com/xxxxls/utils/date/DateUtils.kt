package com.xxxxls.utils.date

import java.util.*
import com.xxxxls.utils.date.TimeConstants.CHINESE_ZODIAC
import com.xxxxls.utils.date.TimeConstants.ZODIAC
import com.xxxxls.utils.date.TimeConstants.ZODIAC_FLAGS


/**
 * 日期帮助类
 * @author Max
 * @date 2020-01-08.
 */
object DateUtils {

    //<editor-fold desc="获取Calendar">
    /**
     * 获取Calendar
     * @param date 指定日期（默认当前日期）
     */
    fun getCalendar(date: Date?): Calendar {
        val calender = Calendar.getInstance()
        if (date != null) {
            calender.time = date
        }
        return calender
    }

    /**
     * 获取Calendar
     * @param millis 指定日期（默认当前日期）
     */
    fun getCalendar(millis: Long?): Calendar {
        val calender = Calendar.getInstance()
        if (millis != null) {
            calender.timeInMillis = millis
        }
        return calender
    }

    //</editor-fold>
    //<editor-fold desc="获取当前时间 指定格式">

    /**
     * 获取当前时间
     * @param format 格式
     */
    fun getCurrentTime(format: String): String {
        return DateFormat.DIY(format).formatDate(Date())
    }

    //</editor-fold>
    //<editor-fold desc="指定日期转换为文本">

    /**
     * 指定日期转换为文本
     * @param format 转换格式
     * @param date 指定日期（默认当前日期）
     */
    fun formatDate(format: String, date: Date?): String {
        return DateFormat.DIY(format).formatDate(date)
    }

    //</editor-fold>
    //<editor-fold desc="指定日期转换为文本 年">

    /**
     * 指定日期转换为文本 年
     * @param date 指定日期（默认当前日期）
     */
    fun formatDateOfYear(date: Date?): String {
        return DateFormat.YEAR.formatDate(date)
    }

    //</editor-fold>
    //<editor-fold desc="指定日期转换为文本 月">

    /**
     * 指定日期转换为文本 月
     * @param date 指定日期（默认当前日期）
     */
    fun formatDateOfMonth(date: Date?): String {
        return DateFormat.MONTH.formatDate(date)
    }

    //</editor-fold>
    //<editor-fold desc="指定日期转换为文本 日">

    /**
     * 指定日期转换为文本 日
     * @param date 指定日期（默认当前日期）
     */
    fun formatDateOfDay(date: Date?): String {
        return DateFormat.DAY.formatDate(date)
    }

    //</editor-fold>
    //<editor-fold desc="指定日期转换为文本 时:分 (24小时制)">
    /**
     * 指定日期转换为文本 时:分 (24小时制)
     * @param date 指定日期（默认当前日期）
     */
    fun formatDateOfHM(date: Date?): String {
        return DateFormat.HM.formatDate(date)
    }

    //</editor-fold>
    //<editor-fold desc="指定日期转换为文本 时:分:秒 (24小时制)">
    /**
     * 指定日期转换为文本 时:分:秒 (24小时制)
     * @param date 指定日期（默认当前日期）
     */
    fun formatDateOfHMS(date: Date?): String {
        return DateFormat.HMS.formatDate(date)
    }

    //</editor-fold>
    //<editor-fold desc="指定日期转换为文本 年-月-日">
    /**
     * 指定日期转换为文本 年-月-日
     * @param date 指定日期（默认当前日期）
     */
    fun formatDateOfYMD(date: Date?): String {
        return DateFormat.YMD.formatDate(date)
    }

    //</editor-fold>
    //<editor-fold desc="指定日期转换为文本 年-月-日 时:分:秒 (24小时制)">
    /**
     * 指定日期转换为文本 年-月-日 时:分:秒 (24小时制)
     * @param date 指定日期（默认当前日期）
     */
    fun formatDateOfYMDHMS(date: Date?): String {
        return DateFormat.YMD_HMS.formatDate(date)
    }
    //</editor-fold>
    //<editor-fold desc="指定日期文本转换为Date">
    /**
     * 指定日期文本转换为Date
     * @param format 转换格式
     * @param dateStr 指定日期（默认当前日期）
     */
    fun parseData(format: String, dateStr: String): Date? {
        return DateFormat.DIY(format).parseData(dateStr)
    }

    //</editor-fold>
    //<editor-fold desc="指定日期文本转换为Date 年">

    /**
     * 指定日期文本转换为Date 年
     * @param dateStr 指定日期（默认当前日期）
     */
    fun parseDataOfYear(dateStr: String): Date? {
        return DateFormat.YEAR.parseData(dateStr)
    }

    //</editor-fold>
    //<editor-fold desc="指定日期文本转换为Date 月">
    /**
     * 指定日期文本转换为Date 月
     * @param dateStr 指定日期（默认当前日期）
     */
    fun parseDataOfMonth(dateStr: String): Date? {
        return DateFormat.MONTH.parseData(dateStr)
    }

    //</editor-fold>
    //<editor-fold desc="指定日期文本转换为Date 日">
    /**
     * 指定日期文本转换为Date 日
     * @param dateStr 指定日期（默认当前日期）
     */
    fun parseDataOfDay(dateStr: String): Date? {
        return DateFormat.DAY.parseData(dateStr)
    }

    //</editor-fold>
    //<editor-fold desc="指定日期文本转换为Date 时:分 (24小时制)">
    /**
     * 指定日期文本转换为Date 时:分 (24小时制)
     * @param dateStr 指定日期（默认当前日期）
     */
    fun parseDataOfHM(dateStr: String): Date? {
        return DateFormat.HM.parseData(dateStr)
    }

    //</editor-fold>
    //<editor-fold desc="指定日期文本转换为Date 时:分:秒 (24小时制)">
    /**
     * 指定日期文本转换为Date 时:分:秒 (24小时制)
     * @param dateStr 指定日期（默认当前日期）
     */
    fun parseDataOfHMS(dateStr: String): Date? {
        return DateFormat.HMS.parseData(dateStr)
    }

    //</editor-fold>
    //<editor-fold desc="指定日期文本转换为Date 年-月-日">
    /**
     * 指定日期文本转换为Date 年-月-日
     * @param dateStr 指定日期（默认当前日期）
     */
    fun parseDataOfYMD(dateStr: String): Date? {
        return DateFormat.YMD.parseData(dateStr)
    }

    //</editor-fold>
    //<editor-fold desc="指定日期文本转换为Date 年-月-日 时:分:秒 (24小时制)">
    /**
     * 指定日期文本转换为Date 年-月-日 时:分:秒 (24小时制)
     * @param dateStr 指定日期（默认当前日期）
     */
    fun parseDataOfYMDHMS(dateStr: String): Date? {
        return DateFormat.YMD_HMS.parseData(dateStr)
    }

    //</editor-fold>
    //<editor-fold desc="获取指定日期的年份">

    /**
     * 获取指定日期的年份
     * @param date 指定日期（默认当前日期）
     */
    fun getYear(date: Date? = Date()): Int {
        return DateFormat.YEAR.formatDate(date).toInt()
    }

    //</editor-fold>

    //<editor-fold desc="获取指定日期的月份">

    /**
     * 获取指定日期的月份
     * @param date 指定日期（默认当前日期）
     */
    fun getMonth(date: Date? = Date()): Int {
        return DateFormat.MONTH.formatDate(date).toInt()
    }

    //</editor-fold>

    //<editor-fold desc="获取指定日期的日">

    /**
     * 获取指定日期的日
     * @param date 指定日期（默认当前日期）
     */
    fun getDay(date: Date? = Date()): Int {
        return DateFormat.DAY.formatDate(date).toInt()
    }

    //</editor-fold>

    //<editor-fold desc="获取指定日期的周几">
    /**
     * 获取指定日期的周几
     * @param date 指定日期（默认当前日期）
     */
    fun getWeek(date: Date? = Date()): Int {
        val calendar = getCalendar(date = date ?: Date())
        return if (calendar.get(Calendar.DAY_OF_WEEK) - 1 < 0) 0 else calendar.get(Calendar.DAY_OF_WEEK) - 1
    }

    //</editor-fold>

    //<editor-fold desc="是否今天">
    /**
     * 是否今天
     * @param date 指定日期
     */
    fun isToDay(date: Date?): Boolean {
        if (date == null) {
            return false
        }
        return isSameDay(date, Date())
    }

    /**
     * 是否今天
     * @param millis 指定日期时间戳
     */
    fun isToDay(millis: Long): Boolean {
        return isSameDay(millis, Date().time)
    }

    //</editor-fold>

    //<editor-fold desc="是否同一天（同年同天）">
    /**
     * 是否同一天（同年同天）
     * @param dateA 日期1
     * @param dateB 日期2
     */
    fun isSameDay(dateA: Date?, dateB: Date?): Boolean {
        if (dateA == null && dateB == null) return true
        return isSameDay(dateA?.time ?: 0L, dateB?.time ?: 0L)
    }

    /**
     * 是否同一天（同年同天）
     * @param millisA 日期1时间戳
     * @param millisB 日期2时间戳
     */
    fun isSameDay(millisA: Long?, millisB: Long?): Boolean {
        if (millisA == null && millisB == null) return true
        return kotlin.math.abs(getTimeSpan(millisA, millisB)) < TimeConstants.DAY
    }

    //</editor-fold>

    //<editor-fold desc="指定日期是否闰年">

    /**
     * 指定日期是否闰年
     * @param date 指定日期（默认当前日期）
     */
    fun isLeapYear(date: Date?): Boolean {
        return isLeapYear(getYear(date))
    }

    /**
     * 是否闰年
     * @param year 年份
     */
    fun isLeapYear(year: Int): Boolean {
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0
    }

    //</editor-fold>

    //<editor-fold desc="指定日期是否上午">

    /**
     * 指定日期是否上午
     * @param date 指定日期（默认当前日期）
     */
    fun isAm(date: Date?): Boolean {
        return getCalendar(date).get(GregorianCalendar.AM_PM) === 0
    }

    /**
     * 指定日期是否上午
     * @param millis 指定日期（默认当前日期）
     */
    fun isAm(millis: Long?): Boolean {
        return getCalendar(millis).get(GregorianCalendar.AM_PM) === 0
    }

    //</editor-fold>

    //<editor-fold desc="指定日期是否下午">
    /**
     * 指定日期是否下午
     * @param date 指定日期（默认当前日期）
     */
    fun isPm(date: Date?): Boolean {
        return !isAm(date)
    }

    /**
     * 指定日期是否下午
     * @param millis 指定日期（默认当前日期）
     */
    fun isPm(millis: Long?): Boolean {
        return !isAm(millis)
    }

    //</editor-fold>

    //<editor-fold desc="指定日期的生肖">
    /**
     * 指定日期的生肖
     * @param date 指定日期（默认当前日期）
     */
    fun getChineseZodiac(date: Date?): String {
        return getChineseZodiac(date?.time)
    }

    /**
     * 指定日期的生肖
     * @param millis 指定日期（默认当前日期）
     */
    fun getChineseZodiac(millis: Long?): String {
        return CHINESE_ZODIAC[getChineseZodiacIndex(millis)]
    }

    /**
     * 指定日期的生肖
     * @param date 指定日期（默认当前日期）
     */
    fun getChineseZodiacIndex(date: Date?): Int {
        return getChineseZodiacIndex(date?.time)
    }

    /**
     * 指定日期的生肖索引 0～11
     * @param millis 指定日期（默认当前日期）
     */
    fun getChineseZodiacIndex(millis: Long?): Int {
        return getCalendar(millis).get(Calendar.YEAR) % 12
    }

    //</editor-fold>

    //<editor-fold desc="指定日期的星座">

    /**
     * 指定日期的星座
     * @param date 指定日期（默认当前日期）
     */
    fun getZodiac(date: Date?): String {
        return getZodiac(date?.time)
    }

    /**
     * 指定日期的星座
     * @param millis 指定日期（默认当前日期）
     */
    fun getZodiac(millis: Long?): String {
        return ZODIAC[getZodiacIndex(millis)]
    }

    /**
     * 指定日期的星座
     * @param month 月份
     * @param day 日
     */
    fun getZodiac(month: Int, day: Int): String {
        return ZODIAC[getZodiacIndex(month, day)]
    }

    /**
     * 指定日期的星座
     * @param date 指定日期（默认当前日期）
     */
    fun getZodiacIndex(date: Date?): Int {
        return getZodiacIndex(date?.time)
    }

    /**
     * 指定日期的星座
     * @param millis 指定日期（默认当前日期）
     */
    fun getZodiacIndex(millis: Long?): Int {
        val calendar = getCalendar(millis)
        val month = calendar.get(Calendar.MONTH) + 1
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        return getZodiacIndex(month, day)
    }

    /**
     * 指定日期的星座 索引 0～11
     * @param month 月份
     * @param day 日
     */
    fun getZodiacIndex(month: Int, day: Int): Int {
        return if (day >= ZODIAC_FLAGS[month - 1])
            month - 1
        else
            (month + 10) % 12
    }

    //</editor-fold>

    //<editor-fold desc="获取当前时间与指定时间的时差">

    /**
     * 获取当前时间与指定时间的时差
     * @param date
     * @return 时差毫秒 如果当前时间大于时间2将返回正数，小于返回负数，等于返回0
     */
    fun getTimeSpanWithToDay(date: Date?): Long {
        return getTimeSpan(Date().time, date?.time)
    }

    /**
     * 获取当前与指定时间的时差
     * @param dateA 时间1
     * @param dateB 时间2
     * @return 时差毫秒 如果时间1大于时间2将返回正数，小于返回负数，等于返回0
     */
    fun getTimeSpan(dateA: Date?, dateB: Date?): Long {
        return getTimeSpan(dateA?.time, dateB?.time)
    }

    /**
     * 获取当前与指定时间的时差
     * @param millisA 时间1
     * @param millisB 时间2
     * @return 时差毫秒 如果时间1大于时间2将返回正数，小于返回负数，等于返回0
     */
    fun getTimeSpan(millisA: Long?, millisB: Long?): Long {
        return (millisA ?: 0L) - (millisB ?: 0L)
    }

    //</editor-fold>

    //<editor-fold desc="获取当前与指定时间的相差年">

    /**
     * 获取当前与指定时间的相差年
     * @param millisA 时间1
     * @param millisB 时间2
     * @return 相差年 如果时间1大于时间2将返回正数，小于返回负数，等于返回0
     */
    //TODO 待测试
    fun getTimeSpanOfYear(millisA: Long?, millisB: Long?): Int {
        val calendarA = getCalendar(millisA)
        val calendarB = getCalendar(millisB)
        return calendarA.get(Calendar.YEAR) - calendarB.get(Calendar.YEAR)
    }

    //</editor-fold>

    //<editor-fold desc="获取当前与指定时间的相差天">
    /**
     * 获取当前与指定时间的相差天
     * @param millisA 时间1
     * @param millisB 时间2
     * @return 相差天 如果时间1大于时间2将返回正数，小于返回负数，等于返回0
     */
    fun getTimeSpanOfDay(millisA: Long?, millisB: Long?): Int {
        val calendarA = getCalendar(millisA)
        val calendarB = getCalendar(millisB)
        return (getTimeSpan(millisA, millisB) / TimeConstants.DAY.toLong()).toInt()
    }

    //</editor-fold>

    //<editor-fold desc="获取当前与指定时间的相差小时">
    /**
     * 获取当前与指定时间的相差小时
     * @param millisA 时间1
     * @param millisB 时间2
     * @return 相差小时 如果时间1大于时间2将返回正数，小于返回负数，等于返回0
     */
    fun getTimeSpanOfHour(millisA: Long?, millisB: Long?): Int {
        val calendarA = getCalendar(millisA)
        val calendarB = getCalendar(millisB)
        return (getTimeSpan(millisA, millisB) / TimeConstants.HOUR.toLong()).toInt()
    }

    //</editor-fold>

    //<editor-fold desc="获取当前与指定时间的相差分钟">
    /**
     * 获取当前与指定时间的相差分钟
     * @param millisA 时间1
     * @param millisB 时间2
     * @return 相差分钟 如果时间1大于时间2将返回正数，小于返回负数，等于返回0
     */
    fun getTimeSpanOfMinute(millisA: Long?, millisB: Long?): Int {
        val calendarA = getCalendar(millisA)
        val calendarB = getCalendar(millisB)
        return (getTimeSpan(millisA, millisB) / TimeConstants.MIN.toLong()).toInt()
    }

    //</editor-fold>

    //<editor-fold desc="指定日期添加指定年">
    /**
     * 指定日期添加指定年
     * @param add 添加的年 (正数相加、负数相减)
     * @param date 指定日期（默认当前日期）
     */
    fun addYear(add: Int, date: Date?): Date {
        val calendar = getCalendar(date)
        calendar.add(Calendar.YEAR, add)
        return calendar.time
    }

    //</editor-fold>

    //<editor-fold desc="指定日期添加指定月">
    /**
     * 指定日期添加指定月
     * @param add 添加的月 (正数相加、负数相减)
     * @param date 指定日期（默认当前日期）
     */
    fun addMonth(add: Int, date: Date?): Date {
        val calendar = getCalendar(date)
        calendar.add(Calendar.MONTH, add)
        return calendar.time
    }

    //</editor-fold>

    //<editor-fold desc="指定日期添加指定月">
    /**
     * 指定日期添加指定月
     * @param add 添加的月 (正数相加、负数相减)
     * @param millis 指定日期时间戳（默认当前日期）
     */
    fun addMonth(add: Int, millis: Long?): Date {
        val calendar = getCalendar(millis)
        calendar.add(Calendar.MONTH, add)
        return calendar.time
    }

    //</editor-fold>

    //<editor-fold desc="指定日期添加指定天">
    /**
     * 指定日期添加指定天
     * @param add 添加的天 (正数相加、负数相减)
     * @param date 指定日期（默认当前日期）
     */
    fun addDay(add: Int, date: Date?): Date {
        val calendar = getCalendar(date)
        calendar.add(Calendar.DATE, add)
        return calendar.time
    }

    /**
     * 指定日期添加指定天
     * @param add 添加的天 (正数相加、负数相减)
     * @param millis 指定日期时间戳（默认当前日期）
     */
    fun addDay(add: Int, millis: Long?): Date {
        val calendar = getCalendar(millis)
        calendar.add(Calendar.DATE, add)
        return calendar.time
    }

    //</editor-fold>

    //<editor-fold desc="指定日期添加指定小时">
    /**
     * 指定日期添加指定小时
     * @param add 添加的小时 (正数相加、负数相减)
     * @param date 指定日期（默认当前日期）
     */
    fun addHour(add: Int, date: Date?): Date {
        val calendar = getCalendar(date)
        calendar.add(Calendar.HOUR, add)
        return calendar.time
    }

    /**
     * 指定日期添加指定小时
     * @param add 添加的小时 (正数相加、负数相减)
     * @param millis 指定日期时间戳（默认当前日期）
     */
    fun addHour(add: Int, millis: Long?): Date {
        val calendar = getCalendar(millis)
        calendar.add(Calendar.HOUR, add)
        return calendar.time
    }

    //</editor-fold>

    //<editor-fold desc="指定日期添加指定分钟">
    /**
     * 指定日期添加指定分钟
     * @param add 添加的分钟 (正数相加、负数相减)
     * @param date 指定日期（默认当前日期）
     */
    fun addMinute(add: Int, date: Date?): Date {
        val calendar = getCalendar(date)
        calendar.add(Calendar.MINUTE, add)
        return calendar.time
    }

    /**
     * 指定日期添加指定分钟
     * @param add 添加的分钟 (正数相加、负数相减)
     * @param millis 指定日期时间戳（默认当前日期）
     */
    fun addMinute(add: Int, millis: Long?): Date {
        val calendar = getCalendar(millis)
        calendar.add(Calendar.MINUTE, add)
        return calendar.time
    }

    //</editor-fold>

    //<editor-fold desc="指定日期添加指定秒">
    /**
     * 指定日期添加指定秒
     * @param add 添加的秒 (正数相加、负数相减)
     * @param date 指定日期（默认当前日期）
     */
    fun addSecondes(add: Int, date: Date?): Date {
        val calendar = getCalendar(date)
        calendar.add(Calendar.SECOND, add)
        return calendar.time
    }

    /**
     * 指定日期添加指定秒
     * @param add 添加的秒 (正数相加、负数相减)
     * @param millis 指定日期时间戳（默认当前日期）
     */
    fun addSecondes(add: Int, millis: Long?): Date {
        val calendar = getCalendar(millis)
        calendar.add(Calendar.SECOND, add)
        return calendar.time
    }

    //</editor-fold>

    //<editor-fold desc="获取指定日期的凌晨（00:00:00）的时间戳">
    /**
     * 获取指定日期的凌晨（00:00:00）的时间戳
     * @param date 指定日期（默认当前日期）
     */
    fun getWeeOfDate(date: Date?): Long {
        val calendar = Calendar.getInstance()
        date?.let {
            calendar.time = it
        }
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar.timeInMillis
    }
    //</editor-fold>

}