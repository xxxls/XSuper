package com.xxxxls.utils.ktx

import java.math.BigDecimal


//<editor-fold desc="转换W（万）">

/**
 * 转换W（万）
 */
fun Long?.toW(): String {
    if (this == null) {
        return "0"
    }
    return toShortCount(9999, "w")
}

//</editor-fold>
//<editor-fold desc="转换K（千）">
/**
 * 转换K（千）
 */
fun Long?.toK(): String {
    if (this == null) {
        return "0"
    }
    return toShortCount(999, "k")
}

//</editor-fold>
//<editor-fold desc="转换简单数量">

/**
 * 转换简单数量
 * @param num 大小 如：9，999，99999
 * @param suffix 后缀 如：千，万，w
 */
fun Long.toShortCount(num: Long = 9999, suffix: String = "万"): String {
    var count = this.toString()
    if (this > num) {
        count = BigDecimal(this)
            .divide(BigDecimal(num + 1), 1, BigDecimal.ROUND_DOWN)
            .toString()
            .plus(suffix)
    }
    return count
}

//</editor-fold>
//<editor-fold desc="转换成百分比字符串">

/**
 * 转换成百分比
 */
fun Long.toPercentStr(num: Long): String {
    if (num == 0L || this < 0) {
        return "0%"
    }
    if (this > num) {
        return "100%"
    }
    return BigDecimal(this)
        .multiply(BigDecimal(100))
        .divide(BigDecimal(num), 0, BigDecimal.ROUND_HALF_UP)
        .toString().plus("%")
}

//</editor-fold>
//<editor-fold desc="转换成百分比">
/**
 * 转换成百分比
 */
fun Long.toPercentInt(sum: Long): Int {
    if (sum == 0L) {
        return 0
    }
    return BigDecimal(this)
        .multiply(BigDecimal(100))
        .divide(BigDecimal(sum), 0, BigDecimal.ROUND_HALF_UP)
        .toInt()
}
//</editor-fold>