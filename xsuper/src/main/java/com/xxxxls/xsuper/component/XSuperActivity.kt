package com.xxxxls.xsuper.component

import android.content.Context
import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner

/**
 * Super-Activity
 * @author Max
 * @date 2019-11-26.
 */
open class XSuperActivity : AppCompatActivity(), IComponent {

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
        onInitialize()
    }

    /**
     * 组件初始化方法，只会调用一次
     */
    protected open fun onInitialize() {}

    /**
     * 内容布局ID
     */
    protected open fun getLayoutResId(): Int {
        return -1
    }

    override fun getContext(): Context? {
        return this
    }

    override fun getLifecycleOwner(): LifecycleOwner? {
        return this
    }

}