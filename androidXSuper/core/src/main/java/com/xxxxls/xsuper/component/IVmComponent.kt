package com.xxxxls.xsuper.component

import android.util.Log
import androidx.lifecycle.HasDefaultViewModelProviderFactory
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelStoreOwner
import com.xxxxls.xsuper.model.ResultLiveData
import com.xxxxls.xsuper.component.bridge.ComponentAction
import com.xxxxls.xsuper.component.bridge.ComponentActionHandler
import com.xxxxls.xsuper.viewmodel.SuperViewModel

/**
 * 组件的viewModel
 * @author Max
 * @date 2019-12-05.
 */
interface IVmComponent : IComponent, ViewModelStoreOwner, HasDefaultViewModelProviderFactory,
    ComponentActionHandler {

    /**
     * 初始化LiveData监听（在这个方法里监听liveData）
     */
    fun onInitObserve() {

    }

    /**
     * 添加ViewModel（关联组件与vm的通信）
     */
    fun addViewModel(viewModel: SuperViewModel?) {
        //建立组件与ViewModel的通信
        getLifecycleOwner()?.let { lifecycleOwner ->
            viewModel?.componentBridgeLiveData?.observe(lifecycleOwner, {
                onAction(it)
            })
        } ?: let {
            Log.e("IVmComponent", "warning/警告: lifecycleOwner is NULL")
        }
    }

    /**
     * 有新的事件
     * @param action 具体事件动作
     */
    override fun onAction(action: ComponentAction) {
    }

    /**
     * 监听liveData
     */
    fun <T> ResultLiveData<T>.observe(
        success: (value: T) -> Unit = {},
        failure: (throwable: Throwable) -> Unit = {}
    ) {
        getLifecycleOwner()?.let { lifecycleOwner ->
            this.observe(lifecycleOwner, success, failure)
        } ?: let {
            Log.e("IVmComponent", "warning/警告: lifecycleOwner is NULL")
        }
    }
}