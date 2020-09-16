package com.xxxxls.xsuper.net.repository

import androidx.annotation.CallSuper
import com.xxxxls.xsuper.loading.ILoading
import com.xxxxls.xsuper.net.callback.XSuperCallBack
import com.xxxxls.xsuper.net.callback.LoadingCallBack
import com.xxxxls.xsuper.net.bridge.ComponentAction
import com.xxxxls.xsuper.net.bridge.IComponentBridge
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext


/**
 * Super - Repository（网络接口，数据库，等..）
 * @author Max
 * @date 2019-11-26.
 */
abstract class XSuperRepository : ILoading {

    //与组件通信桥
    protected var componentBridge: IComponentBridge? = null

    private val job = SupervisorJob()

    private val scope = CoroutineScope(Dispatchers.IO + job)

    /**
     * 关联组件交互
     */
    fun attachComponentBridge(bridge: IComponentBridge?) {
        this.componentBridge = bridge
    }

    /**
     * 启动一个协程任务
     */
    @Deprecated("即将移除该API")
    fun launch(
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> Unit
    ): Job {
        return scope.launch(context, start, block)
    }

    /**
     * 创建一个协程任务（带返回值）
     */
    @Deprecated("即将移除该API")
    fun <T> async(
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> T
    ): Deferred<T> {
        return scope.async(context, start, block)
    }

    /**
     * 清理相关资源
     */
    @CallSuper
    open fun onCleared() {
        job.cancel()
        componentBridge = null
    }

    //toast
    protected fun toast(message: CharSequence) {
        componentBridge?.onAction(ComponentAction.Toast(message))
    }

    /**
     * show loading
     */
    protected fun XSuperCallBack<*>.showLoading() {
        if (this is LoadingCallBack<*> && this.isShowLoading()) {
            this@XSuperRepository.showLoading()
        }
    }

    /**
     * dismiss Loading
     */
    protected fun XSuperCallBack<*>.dismissLoading() {
        if (this is LoadingCallBack<*> && this.isShowLoading()) {
            this@XSuperRepository.dismissLoading()
        }
    }

    override fun showLoading(id: Int?, message: CharSequence?) {
        componentBridge?.onAction(ComponentAction.ShowLoading(id, message))
    }

    override fun dismissLoading(id: Int?) {
        componentBridge?.onAction(ComponentAction.DismissLoading(id))
    }
}

