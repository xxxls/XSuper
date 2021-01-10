package com.xxxxls.utils

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import java.util.concurrent.ConcurrentHashMap

/**
 * 生命周期任务
 * @author Max
 * @date 2020/9/17.
 */
interface LifecycleTask {

    /**
     * 获取生命周期
     */
    fun getLifecycle(): Lifecycle?

    /**
     * 添加一个生命周期任务
     * @param event 生命周期事件
     * @param block 可运行函数
     */
    fun addLifecycleTask(event: Lifecycle.Event, block: () -> Unit)

    /**
     * 删除生命周期任务
     * @param event 生命周期事件
     * @param block 可运行函数
     */
    fun removeLifecycleTask(event: Lifecycle.Event, block: () -> Unit)

    /**
     * 取消指定生命周期任务
     * @param event 生命周期事件（ON_ANY：清空所有）
     */
    fun cancelLifecycleTask(event: Lifecycle.Event)

    /**
     * LifecycleTask 所有者
     */
    interface LifecycleTaskOwner {
        fun getLifecycleTask(): LifecycleTask
    }


    /**
     * 默认实现
     */
    companion object
    class LifecycleTaskImpl(private val lifecycle: Lifecycle) :
        LifecycleTask {

        private val taskMap: ConcurrentHashMap<Lifecycle.Event, HashSet<(() -> Unit)>> by lazy {
            ConcurrentHashMap<Lifecycle.Event, HashSet<(() -> Unit)>>()
        }

        private var lifecycleObserver: LifecycleObserver? = null

        /**
         * 检查绑定生命周期事件
         */
        private fun checkLifecycleEvent() {
            if (lifecycleObserver == null) {
                lifecycleObserver = object : LifecycleObserver {
                    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
                    fun onAny(owner: LifecycleOwner?, event: Lifecycle.Event?) {
                        taskMap.remove(event)?.forEach {
                            it.invoke()
                        }
                        if (event == Lifecycle.Event.ON_DESTROY) {
                            release()
                        }
                    }
                }.apply {
                    getLifecycle()?.addObserver(this)
                }
            }
        }

        /**
         * 释放
         */
        private fun release() {
            taskMap.clear()
            lifecycleObserver?.let {
                getLifecycle()?.removeObserver(it)
                lifecycleObserver = null
            }
        }

        override fun getLifecycle(): Lifecycle? {
            return lifecycle
        }

        override fun addLifecycleTask(event: Lifecycle.Event, block: () -> Unit) {
            checkLifecycleEvent()
            if (!taskMap.containsKey(event)) {
                taskMap[event] = HashSet()
            }
            taskMap[event]?.add(block)
        }

        override fun removeLifecycleTask(event: Lifecycle.Event, block: () -> Unit) {
            taskMap[event]?.remove(block)
        }

        override fun cancelLifecycleTask(event: Lifecycle.Event) {
            if (event == Lifecycle.Event.ON_ANY) {
                taskMap.clear()
            } else {
                taskMap[event]?.clear()
            }
        }
    }
}