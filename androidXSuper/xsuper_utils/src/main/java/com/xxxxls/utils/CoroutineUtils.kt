package com.xxxxls.utils

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

/**
 * 协程帮助类
 * @author Max
 * @date 2020-01-08.
 */
object CoroutineUtils {

    /**
     * io协程，运行在io线程
     */
    fun io(
        start: CoroutineStart = CoroutineStart.DEFAULT, block: suspend CoroutineScope.() -> Unit
    ): Job {
        return GlobalScope.launch(Dispatchers.IO, start) {
            block()
        }
    }

    /**
     * 可以绑定生命周期的io协程
     */
    fun io(
        lifecycleOwner: LifecycleOwner,
        start: CoroutineStart = CoroutineStart.DEFAULT, block: suspend CoroutineScope.() -> Unit
    ): Job {
        val supervisorJob = SupervisorJob()
        CoroutineScope(Dispatchers.IO + supervisorJob).launch(start = start) {
            block()
        }
        lifecycleOwner.lifecycle.addObserver(object : LifecycleObserver {
            @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
            fun onDestroy() {
                supervisorJob.cancel()
                lifecycleOwner.lifecycle.removeObserver(this)
            }
        })
        return supervisorJob
    }

    /**
     * 运行在主线程的协程
     */
    fun main(
        start: CoroutineStart = CoroutineStart.DEFAULT, block: suspend CoroutineScope.() -> Unit
    ): Job {
        return GlobalScope.launch(Dispatchers.Main, start) {
            block()
        }
    }

    /**
     * 可以绑定生命周期的main协程
     */
    fun main(
        lifecycleOwner: LifecycleOwner,
        start: CoroutineStart = CoroutineStart.DEFAULT, block: suspend CoroutineScope.() -> Unit
    ): Job {
        val supervisorJob = SupervisorJob()
        CoroutineScope(Dispatchers.Main + supervisorJob).launch(start = start) {
            block()
        }
        lifecycleOwner.lifecycle.addObserver(object : LifecycleObserver {
            @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
            fun onDestroy() {
                supervisorJob.cancel()
                lifecycleOwner.lifecycle.removeObserver(this)
            }
        })
        return supervisorJob
    }

    /**
     * 有返回值的协程
     */
    fun <T> async(
        context: CoroutineContext = Dispatchers.IO,
        start: CoroutineStart = CoroutineStart.DEFAULT, block: suspend CoroutineScope.() -> T
    ): Deferred<T> {
        return GlobalScope.async(context, start) {
            block()
        }
    }
}


fun Any?.main(
    start: CoroutineStart = CoroutineStart.DEFAULT, block: suspend CoroutineScope.() -> Unit
): Job {
    return CoroutineUtils.main(start, block)
}

fun Any?.main(
    lifecycleOwner: LifecycleOwner,
    start: CoroutineStart = CoroutineStart.DEFAULT, block: suspend CoroutineScope.() -> Unit
): Job {
    return CoroutineUtils.main(lifecycleOwner, start, block)
}

fun <T> Any?.async(
    context: CoroutineContext = Dispatchers.IO,
    start: CoroutineStart = CoroutineStart.DEFAULT, block: suspend CoroutineScope.() -> T
): Deferred<T> {
    return CoroutineUtils.async(context, start, block)
}

fun Any?.io(
    start: CoroutineStart = CoroutineStart.DEFAULT, block: suspend CoroutineScope.() -> Unit
): Job {
    return CoroutineUtils.io(start, block)
}

fun Any?.io(
    lifecycleOwner: LifecycleOwner,
    start: CoroutineStart = CoroutineStart.DEFAULT,
    block: suspend CoroutineScope.() -> Unit
): Job {
    return CoroutineUtils.io(lifecycleOwner, start, block)
}