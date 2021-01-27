package com.xxxxls.xsuper.component

import android.content.Context
import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.ViewModelProvider
import com.xxxxls.utils.L
import com.xxxxls.utils.LifecycleTask
import com.xxxxls.utils.ktx.toast
import com.xxxxls.xsuper.component.bridge.ComponentAction
import com.xxxxls.xsuper.loading.ILoading

/**
 * Super-Activity
 * @author Max
 * @date 2019-11-26.
 */
open class SuperActivity : AppCompatActivity(), IComponent, IVmComponent, ILoading,
    LifecycleTask.LifecycleTaskOwner {

    // 生命周期任务
    private val lifecycleTask: LifecycleTask.LifecycleTaskImpl by lazy {
        LifecycleTask.LifecycleTaskImpl(lifecycle)
    }

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())
        onInitialize()
    }

    /**
     * 组件初始化方法，只会调用一次
     */
    protected open fun onInitialize() {
        onInitObserve()
    }

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
            else -> {

            }
        }
    }

    override fun showLoading(id: Int?, message: CharSequence?) {
    }

    override fun dismissLoading(id: Int?) {
    }

    override fun getLifecycleTask(): LifecycleTask {
        return lifecycleTask
    }

}