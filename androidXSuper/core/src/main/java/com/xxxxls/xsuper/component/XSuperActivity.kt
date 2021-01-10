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
open class XSuperActivity : AppCompatActivity(), IComponent, IVmComponent, ILoading,
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

    //TODO 依赖版本错乱导致此方法没有被实现（应该由activity默认实现该方法的）待解决...
    private var mDefaultFactory: ViewModelProvider.Factory? = null
    override fun getDefaultViewModelProviderFactory(): ViewModelProvider.Factory {
        checkNotNull(application) {
            ("Your activity is not yet attached to the "
                    + "Application instance. You can't request ViewModel before onCreate call.")
        }
        if (mDefaultFactory == null) {
            mDefaultFactory = SavedStateViewModelFactory(
                application,
                this,
                if (intent != null) intent.extras else null
            )
        }
        return mDefaultFactory!!
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

    override fun showLoading(id: Int?, message: CharSequence?) {
        L.e("${javaClass.simpleName} -> showLoading()")
    }

    override fun dismissLoading(id: Int?) {
        L.e("${javaClass.simpleName} -> dismissLoading()")
    }

    override fun getLifecycleTask(): LifecycleTask {
        return lifecycleTask
    }

}