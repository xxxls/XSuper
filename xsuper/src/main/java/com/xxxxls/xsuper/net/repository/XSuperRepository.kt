package com.xxxxls.xsuper.net.repository

import androidx.annotation.CallSuper
import androidx.annotation.NonNull
import com.xxxxls.xsuper.exceptions.XSuperException
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
open class XSuperRepository {

    //与组件通信桥
    protected var mComponentBridge: IComponentBridge? = null

    private val mJob = SupervisorJob()

    private val mScope = CoroutineScope(Dispatchers.IO + mJob)

    fun setComponentBridge(bridge: IComponentBridge?) {
        this.mComponentBridge = bridge
    }

    /**
     * 启动一个协程任务
     */
    fun launch(
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> Unit
    ): Job {
        return mScope.launch(context, start, block)
    }

    /**
     * 创建一个协程任务（带返回值）
     */
    fun <T> async(
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> T
    ): Deferred<T> {
        return mScope.async(context, start, block)
    }

    /**
     * 清理相关资源
     */
    @CallSuper
    open fun onCleared() {
        mJob.cancel()
        mComponentBridge = null
    }

    //弹窗加载弹窗
    protected fun showLoading() {
        mComponentBridge?.onAction(ComponentAction.ShowLoading())
    }

    //关闭加载弹窗
    protected fun dismissLoading() {
        mComponentBridge?.onAction(ComponentAction.DismissLoading())
    }

    //toast
    protected fun toast(message: CharSequence) {
        mComponentBridge?.onAction(ComponentAction.Toast(message))
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
}

