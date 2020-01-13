package com.xxxxls.utils.ktx

import com.xxxxls.utils.UiUtils

/**
 * UI
 * @author Max
 * @date 2020-01-10.
 */

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