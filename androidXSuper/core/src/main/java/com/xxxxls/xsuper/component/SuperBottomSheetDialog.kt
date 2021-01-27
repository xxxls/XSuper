package com.xxxxls.xsuper.component

import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.xxxxls.xsuper.R


/**
 * Super - BottomSheetDialog
 * @author Max
 * @date 2019-11-26.
 */
open class SuperBottomSheetDialog : BottomSheetDialog {

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

    //初始化
    private fun initBottomSheetBehavior() {
        // 外部点击取消
        setCanceledOnTouchOutside(getCanceled())

        val bottomSheetLayout = getBottomSheetLayout()
        if (bottomSheetLayout != null) {
            val layoutParams = bottomSheetLayout.layoutParams as? CoordinatorLayout.LayoutParams
            val height = getMaxHeight()
            if (height > 0) {
                //设置高度
                layoutParams?.height = getMaxHeight()
            }
            val state = if (isExpandedState()) {
                BottomSheetBehavior.STATE_EXPANDED
            } else {
                BottomSheetBehavior.STATE_COLLAPSED
            }

            //设置展开状态
            val behavior = BottomSheetBehavior.from(bottomSheetLayout)
            val peekHeight = getPeekHeight()
            if (peekHeight > 0) {
                //设置Peek高度
                behavior.peekHeight = peekHeight
            }
            behavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                override fun onStateChanged(bottomSheet: View, state: Int) {
                    if (state == BottomSheetBehavior.STATE_DRAGGING) {
                        //判断为向下拖动行为时，则强制设定状态为展开
                        if (!isCanSlideClose()) {
                            behavior.state = BottomSheetBehavior.STATE_EXPANDED
                        }
                    } else if (state == BottomSheetBehavior.STATE_HIDDEN) {
                        dismiss()
                    }
                }

                override fun onSlide(bottomSheet: View, slideOffset: Float) {
                }
            })
            behavior.state = state
        }
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

    /**
     * 获取要显示的最大高度（最大展开高度）
     */
    protected open fun getMaxHeight(): Int {
        return -1
    }

    /**
     * 获取要显示的Peek高度（中途停顿高度）
     */
    protected open fun getPeekHeight(): Int {
        return -1
    }

    /**
     * 是否展开状态
     */
    protected open fun isExpandedState(): Boolean {
        return true
    }

    /**
     * 获取显示布局
     */
    protected open fun getBottomSheetLayout(): View? {
        return delegate?.findViewById(R.id.design_bottom_sheet)
    }

}
