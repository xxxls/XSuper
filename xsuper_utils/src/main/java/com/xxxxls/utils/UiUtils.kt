package com.xxxxls.utils

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.annotation.NonNull

/**
 *
 * @author Max
 * @date 2019-12-10.
 */

fun Long.dp(): Int {
    return UiUtils.px2dip(this.toFloat())
}


fun Float.dp(): Int {
    return UiUtils.px2dip(this)
}


fun Int.dp(): Int {
    return UiUtils.px2dip(this.toFloat())
}


fun Long.px(): Int {
    return UiUtils.dip2px(this.toFloat())
}


fun Float.px(): Int {
    return UiUtils.dip2px(this)
}


fun Int.px(): Int {
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
    fun dip2px(dpValue: Float): Int {
        val scale = AppUtils.getApp().resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    fun px2dip(pxValue: Float): Int {
        val scale = AppUtils.getApp().resources.displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }

}
