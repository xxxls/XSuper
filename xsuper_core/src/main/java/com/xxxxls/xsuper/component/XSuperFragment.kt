package com.xxxxls.xsuper.component

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import com.xxxxls.xsuper.loading.ILoading
import com.xxxxls.xsuper.net.bridge.ComponentAction
import com.xxxxls.xsuper.net.bridge.IComponentBridge
import com.xxxxls.utils.ktx.toast
import com.xxxxls.xsuper.support.LifecycleTask

/**
 * Super-Fragment
 * @author Max
 * @date 2019-11-26.
 */
open class XSuperFragment : XSuperLazyFragment(), IComponent, IComponentViewModel, ILoading,
    LifecycleTask.LifecycleTaskOwner {

    // 生命周期任务
    private val lifecycleTask: LifecycleTask.LifecycleTaskImpl by lazy {
        LifecycleTask.LifecycleTaskImpl(lifecycle)
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

    override fun onFragmentFirstVisible() {
        super.onFragmentFirstVisible()
        onInitialize()
    }

    /**
     * 组件初始化方法，只会调用一次
     */
    protected open fun onInitialize() {
        onInitObserve()
    }

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

    override fun getLifecycleOwner(): LifecycleOwner {
        return this
    }

    override fun <T : View> findViewById(id: Int): T? {
        return view?.findViewById<T>(id)
    }

    override fun onAction(action: ComponentAction) {
        super.onAction(action)
        com.xxxxls.utils.L.e("${javaClass.simpleName} -> onAction() action:${action}")

        (activity as? IComponentBridge)?.run {
            //转至activity处理
            this.onAction(action)
        } ?: let {
            //自己处理该事件
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
                    action.build(activity)
                }
            }
        }
    }

    override fun showLoading(id: Int?, message: CharSequence?) {
        com.xxxxls.utils.L.e("${javaClass.simpleName} -> showLoading()")
        (activity as? ILoading)?.run {
            //转至activity处理
            this.showLoading(id, message)
        }
    }

    override fun dismissLoading(id: Int?) {
        com.xxxxls.utils.L.e("${javaClass.simpleName} -> dismissLoading()")
        (activity as? ILoading)?.run {
            //转至activity处理
            this.dismissLoading(id)
        }
    }

    override fun getLifecycleTask(): LifecycleTask {
        return lifecycleTask
    }

}