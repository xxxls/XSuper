package com.xxxxls.xsuper.component

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.LayoutRes
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.lifecycle.LifecycleOwner
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.xxxxls.xsuper.R

/**
 * Super - BottomSheetDialogFragment
 * @author Max
 * @date 2019-11-26.
 */
open class XSuperBottomSheetDialogFragment : BottomSheetDialogFragment(),
    IComponent {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        getLayoutResId()?.let {
            return layoutInflater.inflate(it, container, false)
        } ?: let {
            return createView()
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        onInitialize()
        initBottomSheetBehavior()
    }

    //初始化
    private fun initBottomSheetBehavior() {

        val dialog = dialog as BottomSheetDialog
        // 外部点击取消
        dialog.setCanceledOnTouchOutside(getCanceled())

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
     * 初始化方法，只会调用一次
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

    override fun getLifecycleOwner(): LifecycleOwner {
        return this
    }

    override fun <T : View> findViewById(id: Int): T? {
        return view?.findViewById<T>(id)
    }

    /**
     * 获取显示布局
     */
    protected fun getBottomSheetLayout(): View? {
        val dialog = dialog as? BottomSheetDialog
        return dialog?.delegate?.findViewById(R.id.design_bottom_sheet)
    }

    protected fun getBottomSheetBehavior(): BottomSheetBehavior<View>? {
        getBottomSheetLayout()?.let {
            return BottomSheetBehavior.from(it)
        }
        return null
    }

    /**
     * 获取要显示的Peek高度（中途停顿高度）
     */
    protected open fun getPeekHeight(): Int {
        return -1
    }

    /**
     * 获取要显示的最大高度（最大展开高度）
     */
    protected open fun getMaxHeight(): Int {
        return -1
    }

    /**
     * 是否展开状态
     */
    protected open fun isExpandedState(): Boolean {
        return true
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
