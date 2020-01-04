package com.xxxxls.titlebar

import android.graphics.drawable.Drawable

/**
 * titlebar 样式
 * @author Max
 * @date 2020-01-03.
 */
interface ITitleBarStyle {

    /**
     * 标题栏背景
     */
    fun getBackground(): Drawable

    /**
     * 标题栏高度
     */
    fun getHeight(): Int

    /**
     * 获取左右标题条目的左右内边距
     */
    fun getChildLeftRightPadding(): Int

    /**
     * 主标题与副标题间距
     */
    fun getTitleMargin(): Int

    /**
     * 获取文本与图标边距(左，右条目)
     */
    fun getDrawablePadding(): Float

    /**
     * 标题文本大小
     */
    fun getTitleTextSize(): Float

    /**
     * 副标题文本大小
     */
    fun getSubTitleTextSize(): Float

    /**
     * 标题文本颜色
     */
    fun getTitleTextColor(): Int

    /**
     * 副标题文本颜色
     */
    fun getSubTitleTextColor(): Int

    /**
     * 左标题文本大小
     */
    fun getLeftTextSize(): Float

    /**
     * 左标题文本颜色
     */
    fun getLeftTextColor(): Int

    /**
     * 左图标（一般是返回按钮）
     */
    fun getLeftIcon(): Drawable?

    /**
     * 左标题栏背景
     */
    fun getLeftBackground(): Drawable?

    /**
     * 右标题文本大小
     */
    fun getRightTextSize(): Float

    /**
     * 右标题文本颜色
     */
    fun getRightTextColor(): Int

    /**
     * 右标题栏背景
     */
    fun getRightBackground(): Drawable?

    /**
     * 分割线背景
     */
    fun getLineDrawable(): Drawable?

    /**
     * 分割线高度
     */
    fun getLineHeight(): Int?
}