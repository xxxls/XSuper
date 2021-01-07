package com.xxxxls.utils.ktx

import com.xxxxls.utils.UiUtils
import kotlin.math.roundToInt

/**
 * UI
 * @author Max
 * @date 2020-01-10.
 */


/**
 * 转换为PX值
 */
val Float.dp: Int get() = this.toPX().roundToInt()
val Int.dp: Int get() =  this.toPX().roundToInt()

/**
 * 转换为DP值
 */
val Float.px: Int get() = this.toDP().roundToInt()
val Int.px: Int get() = this.toDP().roundToInt()


fun Long.toDP(): Float {
    return UiUtils.px2dip(this.toFloat())
}


fun Float.toDP(): Float {
    return UiUtils.px2dip(this)
}


fun Int.toDP(): Float {
    return UiUtils.px2dip(this.toFloat())
}


fun Long.toPX(): Float {
    return UiUtils.dip2px(this.toFloat())
}


fun Float.toPX(): Float {
    return UiUtils.dip2px(this)
}


fun Int.toPX(): Float {
    return UiUtils.dip2px(this.toFloat())
}