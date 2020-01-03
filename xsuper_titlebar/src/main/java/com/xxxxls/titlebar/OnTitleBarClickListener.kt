package com.xxxxls.titlebar

import android.view.View

/**
 * 标题栏点击事件
 * @author Max
 * @date 2020-01-03.
 */
open class OnTitleBarClickListener : OnTitleClickListener, OnSubTitleClickListener,
    OnLeftTitleClickListener, OnRightTitleClickListener {
    override fun onLeftTitleClick(view: View) {
    }

    override fun onRightTitleClick(view: View) {
    }

    override fun onTitleClick(view: View) {
    }

    override fun onSubTitleClick(view: View) {
    }

}