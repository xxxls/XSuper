package com.xxxxls.utils

import android.app.Activity
import android.content.Context
import android.util.DisplayMetrics
import android.util.TypedValue

/**
 *
 * @author Max
 * @date 2019-12-10.
 */

fun Long.dp(): Float {
    return UiUtils.px2dip(this.toFloat())
}


fun Float.dp(): Float {
    return UiUtils.px2dip(this)
}


fun Int.dp(): Float {
    return UiUtils.px2dip(this.toFloat())
}


fun Long.px(): Float {
    return UiUtils.dip2px(this.toFloat())
}


fun Float.px(): Float {
    return UiUtils.dip2px(this)
}


fun Int.px(): Float {
    return UiUtils.dip2px(this.toFloat())
}

object UiUtils {
    fun getScreenWidth(context: Context): Int {
        val localDisplayMetrics = DisplayMetrics()
        (context as Activity).windowManager.defaultDisplay.getMetrics(localDisplayMetrics)
        return localDisplayMetrics.widthPixels
    }

    fun getScreenHeight(context: Context): Int {
        val localDisplayMetrics = DisplayMetrics()
        (context as Activity).windowManager.defaultDisplay.getMetrics(localDisplayMetrics)
        return localDisplayMetrics.heightPixels
    }

    fun getScreenRatio(context: Context): Float {
        return getScreenWidth(context) * 1.0f / getScreenHeight(
            context
        )
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    fun dip2px(dpValue: Float): Float {
        return dip2px(AppUtils.getApp(), dpValue)
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    fun px2dip(pxValue: Float): Float {
        return px2dip(AppUtils.getApp(), pxValue)
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    fun dip2px(context: Context, dpValue: Float): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dpValue,
            context.resources.displayMetrics
        )
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    fun px2dip(context: Context, pxValue: Float): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_PX,
            pxValue,
            context.resources.displayMetrics
        )
    }

}
