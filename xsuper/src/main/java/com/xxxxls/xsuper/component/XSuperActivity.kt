package com.xxxxls.xsuper.component

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.xxxxls.xsuper.exceptions.XSuperException
import com.xxxxls.xsuper.loading.ILoading
import com.xxxxls.xsuper.net.bridge.ComponentAction
import com.xxxxls.xsuper.util.L
import com.xxxxls.xsuper.util.toast

/**
 * Super-Activity
 * @author Max
 * @date 2019-11-26.
 */
open class XSuperActivity : AppCompatActivity(), IComponent, IComponentViewModel, ILoading {

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

    override fun getContext(): Context {
        return this
    }

    override fun getLifecycleOwner(): LifecycleOwner {
        return this
    }

    override fun onAction(action: ComponentAction) {
        super.onAction(action)
        L.e("${javaClass.simpleName} -> onAction() action:${action}")
        when (action) {
            is ComponentAction.ShowLoading -> {
                showLoading(message = action.message)
            }
            is ComponentAction.DismissLoading -> {
                dismissLoading()
            }
            is ComponentAction.Toast -> {
                toast(action.message)
            }
            is ComponentAction.BuildDialog -> {
                action.build(this)
            }
        }
    }

    override fun showLoading(id: Int, message: CharSequence?) {
        L.e("${javaClass.simpleName} -> showLoading()")
    }

    override fun dismissLoading(id: Int) {
        L.e("${javaClass.simpleName} -> dismissLoading()")
    }

}