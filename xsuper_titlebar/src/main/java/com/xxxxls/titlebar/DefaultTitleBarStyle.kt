package com.xxxxls.titlebar

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import com.xxxxls.titlebar.utils.UiUtils

/**
 * 默认标题栏样式
 * @author Max
 * @date 2020-01-03.
 */
open class DefaultTitleBarStyle(val context: Context) : ITitleBarStyle {

    override fun getChildLeftRightPadding(): Int {
        return UiUtils.dip2px(context, 16f).toInt()
    }

    override fun getTitleMargin(): Int {
        return UiUtils.dip2px(context, 2f).toInt()
    }

    override fun getBackground(): Drawable {
        return ColorDrawable(Color.WHITE)
    }

    override fun getHeight(): Int {
        return UiUtils.dip2px(context, 48f).toInt()
    }

    override fun getLeftTextSize(): Float {
        return UiUtils.dip2px(context, 16f)
    }

    override fun getTitleTextSize(): Float {
        return UiUtils.dip2px(context, 18f)
    }

    override fun getSubTitleTextSize(): Float {
        return UiUtils.dip2px(context, 14f)
    }

    override fun getRightTextSize(): Float {
        return UiUtils.dip2px(context, 16f)
    }

    override fun getLeftTextColor(): Int {
        return Color.BLACK
    }

    override fun getTitleTextColor(): Int {
        return Color.BLACK
    }

    override fun getSubTitleTextColor(): Int {
        return Color.GRAY
    }

    override fun getRightTextColor(): Int {
        return Color.BLACK
    }

    override fun getLeftIcon(): Drawable? {
        return null
    }

    override fun getLineDrawable(): Drawable? {
        return ColorDrawable(Color.GRAY)
    }

    override fun getLineHeight(): Int? {
        return 2
    }

    override fun getDrawablePadding(): Float {
        return UiUtils.dip2px(context, 2f)
    }

}