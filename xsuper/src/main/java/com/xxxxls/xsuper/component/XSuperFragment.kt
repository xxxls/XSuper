package com.xxxxls.xsuper.component

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner

/**
 * Super-Fragment
 * @author Max
 * @date 2019-11-26.
 */
open class XSuperFragment : XSuperLazyFragment(), IComponent {

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

    override fun onFragmentFirstVisible() {
        super.onFragmentFirstVisible()
        onInitialize()
    }

    /**
     * 组件初始化方法，只会调用一次
     */
    protected open fun onInitialize() {}

    /**
     * 内容布局资源ID (优先级高于createContentView)
     */
    protected open fun getLayoutResId(): Int? {
        return null
    }

    /**
     * 创建内容视图(适用于轻量级的Fragment，大多数场景使用 #getLayoutResId)
     */
    protected open fun createView(): View? {
        return null
    }

    override fun getLifecycleOwner(): LifecycleOwner? {
        return this
    }

    override fun <T : View> findViewById(id: Int): T? {
        return view?.findViewById<T>(id)
    }

}