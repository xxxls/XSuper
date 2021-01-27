package com.xxxxls.xsuper.component

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.style.BackgroundColorSpan
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner

/**
 * Super-Dialog
 * @author Max
 * @date 2019-11-26.
 */
open class SuperDialog : Dialog, IComponent {

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, themeResId: Int) : super(context, themeResId) {
        init()
    }

    constructor(
        context: Context,
        cancelable: Boolean,
        cancelListener: DialogInterface.OnCancelListener?
    ) : super(context, cancelable, cancelListener) {
        init()
    }

    protected open fun init() {
        getLayoutResId()?.let {
            setContentView(it)
        } ?: let {
            createView()?.let {
                setContentView(it)
            }
        }
    }

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (isNeedFullScreen()) {
            setFullScreen()
        }
        onInitialize()
    }

    /**
     * 初始化逻辑
     */
    protected open fun onInitialize() {

    }

    /**
     * 创建内容视图
     */
    protected open fun createView(): View? {
        return null
    }

    /**
     * 创建内容视图 根据布局ID
     */
    @LayoutRes
    protected open fun getLayoutResId(): Int? {
        return null
    }

    /**
     * 设置边距
     */
    protected fun setPadding(left: Int, top: Int, right: Int, bottom: Int) {
        window?.apply {
            this.decorView
                .setPadding(left, top, right, bottom)
        }
    }

    /**
     * 设置宽高
     */
    protected fun setSize(
        width: Int = WindowManager.LayoutParams.WRAP_CONTENT,
        height: Int = WindowManager.LayoutParams.WRAP_CONTENT
    ) {
        window?.apply {
            val layoutParams = this.attributes
            layoutParams.width = width
            layoutParams.height = height
            this.attributes = layoutParams
        }
    }

    /**
     * 设置方向
     */
    protected fun setGravity(gravity: Int = Gravity.CENTER) {
        window?.apply {
            val layoutParams = this.attributes
            layoutParams.gravity = gravity
            this.attributes = layoutParams
        }
    }

    /**
     * 设置全屏
     * @param backgroundColor 背景颜色 (默认透明)
     */
    protected fun setFullScreen(backgroundColor: Int = Color.TRANSPARENT) {
        //设置window背景，默认的背景会有Padding值，不能全屏。当然不一定要是透明，你可以设置其他背景，替换默认的背景即可。
        window?.setBackgroundDrawable(ColorDrawable(backgroundColor))
        //一定要在setContentView之后调用，否则无效
        window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
    }

    /**
     * 是否需要全屏
     */
    protected open fun isNeedFullScreen(): Boolean {
        return false
    }

    override fun getLifecycleOwner(): LifecycleOwner? {
        return context as? LifecycleOwner
    }

}