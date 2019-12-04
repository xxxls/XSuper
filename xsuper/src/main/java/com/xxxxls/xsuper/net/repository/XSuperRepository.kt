package com.xxxxls.xsuper.net.repository

import androidx.annotation.CallSuper
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
    private var mComponentBridge: IComponentBridge? = null

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
     * 清理相关资源
     */
    @CallSuper
    open fun onCleared() {
        mJob.cancel()
        mComponentBridge = null
    }
}

