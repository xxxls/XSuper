package com.xxxxls.xsuper.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xxxxls.utils.data.ConcurrentHashMapStore
import com.xxxxls.utils.data.XSuperStore
import com.xxxxls.xsuper.loading.*
import com.xxxxls.xsuper.net.bridge.ComponentAction
import com.xxxxls.xsuper.net.bridge.IComponentBridge
import com.xxxxls.xsuper.net.repository.XSuperRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

/**
 * super - ViewModel
 * @author Max
 * @date 2019-11-29.
 */
open class XSuperViewModel : ViewModel(), IComponentBridge, ILoading {

    /**
     * 与组件(activity，fragment)的通信（加载弹窗，toast等）
     */
    val componentBridgeLiveData: MutableLiveData<ComponentAction> by lazy {
        MutableLiveData<ComponentAction>()
    }

    // 数据仓存储器
    private val store: XSuperStore<Class<*>, XSuperRepository> by lazy {
        ConcurrentHashMapStore<Class<*>, XSuperRepository>()
    }

    /**
     * 创建Repository
     */
    @Deprecated("即将移除")
    protected fun <T : XSuperRepository> createRepository(
        clazz: Class<T>, build: (Class<*>) -> T = {
            (it.newInstance() as T)
        }
    ): T {
        return (store.get(key = clazz, build = build) as T).apply {
            this.attachComponentBridge(this@XSuperViewModel)
        }
    }

    /**
     * 启动一个协程任务
     * @param context 默认IO
     * @param start 默认启动方式
     * @param loading 执行进度弹窗，null表示不使用弹窗
     * @param block 执行的任务
     */
    @UseExperimental(InternalCoroutinesApi::class)
    fun launch(
        context: CoroutineContext = Dispatchers.IO,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        loading: ILoading? = this,
        block: suspend CoroutineScope.() -> Unit
    ): Job {
        return viewModelScope.launch(context, start, block = {
            loading.showLoadingInCoroutine(hashCode())
            block.invoke(this)
        }).apply {
            loading?.run {
                invokeOnCompletion(onCancelling = true, invokeImmediately = true, handler = {
                    // 任务取消
                    this.dismissLoadingInMain(this.hashCode())
                })
                invokeOnCompletion(onCancelling = false, invokeImmediately = true, handler = {
                    // 任务完成
                    this.dismissLoadingInMain(this.hashCode())
                })
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        store.clear()
    }

    /**
     * 中转给组件
     */
    override fun onAction(action: ComponentAction) {
        componentBridgeLiveData.postValue(action)
    }

    override fun showLoading(id: Int?, message: CharSequence?) {
        this.onAction(ComponentAction.ShowLoading(id, message))
    }

    override fun dismissLoading(id: Int?) {
        this.onAction(ComponentAction.DismissLoading(id))
    }


}
