package com.xxxxls.xsuper.util

import android.app.Activity
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

/**
 * 资源工具类
 * @author Max
 * @date 2019-11-26.
 */


/**
 * 获取颜色
 */
fun Fragment.getColorById(@ColorRes colorResId: Int): Int {
    return ContextCompat.getColor(context!!, colorResId)
}


/**
 * 获取图片
 */
fun Fragment.getDrawableById(@DrawableRes drawableRedId: Int): Drawable? {
    return ContextCompat.getDrawable(context!!, drawableRedId)
}


/**
 * 获取颜色
 */
fun Activity.getColorById(@ColorRes colorResId: Int): Int {
    return ContextCompat.getColor(this, colorResId)
}

/**
 * 获取图片
 */
fun Activity.getDrawableById(@DrawableRes drawableRedId: Int): Drawable? {
    return ContextCompat.getDrawable(this, drawableRedId)
}


/**
 * 获取颜色
 */
fun Context.getColorById(@ColorRes colorResId: Int): Int {
    return ContextCompat.getColor(this, colorResId)
}

/**
 * 获取图片
 */
fun Context.getDrawableById(@DrawableRes drawableRedId: Int): Drawable? {
    return ContextCompat.getDrawable(this, drawableRedId)
}
