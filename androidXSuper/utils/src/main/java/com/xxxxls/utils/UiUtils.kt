package com.xxxxls.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Build
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View
import androidx.core.text.TextUtilsCompat
import androidx.core.view.ViewCompat


/**
 *
 * @author Max
 * @date 2019-12-10.
 */


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

    /**
     * 是否从右到左布局
     */
    @SuppressLint("NewApi")
    fun isRtl(view: View): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            View.LAYOUT_DIRECTION_RTL == view.layoutDirection
        } else {
            false
        }
    }

    /**
     * 是否从右到左布局
     */
    fun isRtl(context: Context): Boolean {
        return TextUtilsCompat.getLayoutDirectionFromLocale(
            context.resources.configuration.locale
        ) == ViewCompat.LAYOUT_DIRECTION_RTL
    }

}
