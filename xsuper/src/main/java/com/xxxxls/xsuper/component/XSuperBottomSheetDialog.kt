package com.xxxxls.xsuper.component

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import com.google.android.material.bottomsheet.BottomSheetDialog


/**
 * Super - BottomSheetDialog
 * @author Max
 * @date 2019-11-26.
 */
open class XSuperBottomSheetDialog : BottomSheetDialog {

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, theme: Int) : super(context, theme) {
        init()
    }

    constructor(
        context: Context,
        cancelable: Boolean,
        cancelListener: DialogInterface.OnCancelListener?
    ) : super(context, cancelable, cancelListener) {
        init()
    }

    private fun init() {
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
        onInitialize()
    }

    /**
     * 初始化逻辑
     */
    protected open fun onInitialize(){

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
     * 是否可点击阴影部分取消
     */
    protected open fun getCanceled(): Boolean {
        return true
    }

    /**
     * 是否可以滑动销毁
     */
    protected open fun isCanSlideClose(): Boolean {
        return true
    }

}
