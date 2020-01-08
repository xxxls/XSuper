package com.xxxxls.utils

import android.view.View
import android.widget.Checkable


/**
 * 展示or隐藏
 */
inline fun View.visibleOrGone(show: View.() -> Boolean = { true }) {
    visibility = if (show(this)) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

/**
 * 展示or不可见
 */
inline fun View.visibleOrInvisible(show: View.() -> Boolean = { true }) {
    visibility = if (show(this)) {
        View.VISIBLE
    } else {
        View.INVISIBLE
    }
}

/**
 * 点击事件
 */
inline fun <T : View> T.singleClick(time: Long = 800, crossinline block: (T) -> Unit) {
    setOnClickListener {
        val currentTimeMillis = System.currentTimeMillis()
        if (currentTimeMillis - lastClickTime > time || this is Checkable) {
            lastClickTime = currentTimeMillis
            block(this)
        }
    }
}

/**
 * 点击事件
 */
fun <T : View> T.singleClick(onClickListener: View.OnClickListener, time: Long = 800) {
    setOnClickListener {
        val currentTimeMillis = System.currentTimeMillis()
        if (currentTimeMillis - lastClickTime > time || this is Checkable) {
            lastClickTime = currentTimeMillis
            onClickListener.onClick(this)
        }
    }
}

var <T : View> T.lastClickTime: Long
    set(value) = setTag(1766613352, value)
    get() = getTag(1766613352) as? Long ?: 0


