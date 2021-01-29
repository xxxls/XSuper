package com.xxxxls.titlebar

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable

/**
 * 默认标题栏样式
 * @author Max
 * @date 2020-01-03.
 */
open class DefaultTitleBarStyle(val context: Context) : ITitleBarStyle {
    override fun getLeftBackground(): Drawable? {
        return context.getDrawableById(R.drawable.titlebar_ripple_default)
    }

    override fun getRightBackground(): Drawable? {
        return context.getDrawableById(R.drawable.titlebar_ripple_default)
    }

    override fun getChildLeftRightPadding(): Int {
        return context.dip2px(16f).toInt()
    }

    override fun getTitleMargin(): Int {
        return context.dip2px(2f).toInt()
    }

    override fun getBackground(): Drawable {
        return ColorDrawable(Color.WHITE)
    }

    override fun getHeight(): Int {
        return context.dip2px(48f).toInt()
    }

    override fun getLeftTextSize(): Float {
        return context.dip2px(16f)
    }

    override fun getTitleTextSize(): Float {
        return context.dip2px(18f)
    }

    override fun getSubTitleTextSize(): Float {
        return context.dip2px(14f)
    }

    override fun getRightTextSize(): Float {
        return context.dip2px(16f)
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
        return context.dip2px(2f)
    }

}