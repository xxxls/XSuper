package com.xxxxls.xsuper.component

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelStoreOwner
import com.xxxxls.xsuper.exceptions.XSuperException
import com.xxxxls.xsuper.net.XSuperLiveData
import com.xxxxls.xsuper.net.bridge.ComponentAction
import com.xxxxls.xsuper.net.bridge.IComponentBridge
import com.xxxxls.xsuper.net.viewmodel.XSuperViewModel

/**
 * 组件的viewModel
 * @author Max
 * @date 2019-12-05.
 */
interface IComponentViewModel : ViewModelStoreOwner, IComponentBridge {

    /**
     * 组件的LifecycleOwner
     */
    fun getLifecycleOwner(): LifecycleOwner

    /**
     * 初始化LiveData监听（在这个方法里监听liveData）
     */
    fun onInitObserve() {

    }

    /**
     * 添加ViewModel（关联组件与vm的通信）
     */
    fun addViewModel(viewModel: XSuperViewModel?) {
        //建立组件与ViewModel的通信
        viewModel?.componentBridgeLiveData?.observe(getLifecycleOwner(), Observer<ComponentAction> {
            onAction(it)
        })
    }

    /**
     * 有新的事件
     * @param action 具体事件动作
     */
    override fun onAction(action: ComponentAction) {
    }

    /**
     * 建立关联
     */
    fun <T> XSuperLiveData<T>.observe(
        success: (value: T?) -> Unit = {},
        error: (e: XSuperException) -> Unit = {}
    ) {
        this.observe(getLifecycleOwner(), success, error)
    }

}