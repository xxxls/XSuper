package com.xxxxls.xsuper.component

import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.*
import androidx.annotation.LayoutRes
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.LifecycleOwner

/**
 * Super - DialogFragment
 * @author Max
 * @date 2019-11-26.
 */
open class SuperDialogFragment : DialogFragment(), IComponent {

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onInitialize()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        dialog?.window?.apply {
            setBackgroundDrawable(getDialogBackground())
        }

        if (isFullScreen()) {
            dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
            super.onActivityCreated(savedInstanceState)
            dialog?.window?.apply {
                setLayout(
                    WindowManager.LayoutParams.MATCH_PARENT,
                    WindowManager.LayoutParams.MATCH_PARENT
                )
            }
        } else {
            super.onActivityCreated(savedInstanceState)
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

    /**
     * 是否可点击阴影部分取消
     */
    protected open fun getCanceled(): Boolean {
        return true
    }

    /**
     * 是否全屏
     */
    protected open fun isFullScreen(): Boolean {
        return false
    }

    /**
     * 背景图
     */
    protected open fun getDialogBackground(): Drawable {
        return ColorDrawable(0x00000000)
    }

    override fun getLifecycleOwner(): LifecycleOwner? {
        return this
    }

    override fun <T : View> findViewById(id: Int): T? {
        return view?.findViewById<T>(id)
    }

}
