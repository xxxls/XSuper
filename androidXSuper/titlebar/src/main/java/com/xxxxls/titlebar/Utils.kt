package com.xxxxls.titlebar

import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.TypedValue
import android.view.View
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

/**
 * @author Max
 * @date 1/29/21.
 */
internal class Utils {

}

/**
 * 布局方向是否从右到左
 */
internal fun View.isRtl(): Boolean {
    return View.LAYOUT_DIRECTION_RTL == this.layoutDirection
}

/**
 * 获取图片
 */
internal fun Context.getDrawableById(@DrawableRes drawableRedId: Int): Drawable? {
    return ContextCompat.getDrawable(this, drawableRedId)
}

/**
 * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
 */
internal fun Context.dip2px(dpValue: Float): Float {
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dpValue,
        this.resources.displayMetrics
    )
}