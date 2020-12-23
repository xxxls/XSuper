package com.xxxxls.example.ui.tools.lifecycle

import androidx.lifecycle.Lifecycle
import com.alibaba.android.arouter.facade.annotation.Route
import com.xxxxls.example.bean.SimpleItemBean
import com.xxxxls.example.common.BaseListFragment
import com.xxxxls.module_base.constants.HomePaths

/**
 * 生命周期任务
 * @author Max
 * @date 2020-09-17.
 */
@Route(path = HomePaths.HOME_FRAGMENT_LIFECYCLE_TASK_INDEX)
class LifecycleTaskFragment : BaseListFragment() {

    override fun getTitle(): String {
        return "LifecycleTask"
    }

    override fun getItems(): Array<SimpleItemBean> {
        return arrayOf(
            SimpleItemBean("ON_CREATE"),
            SimpleItemBean("ON_START"),
            SimpleItemBean("ON_RESUME"),
            SimpleItemBean("ON_PAUSE"),
            SimpleItemBean("ON_STOP"),
            SimpleItemBean("ON_DESTROY"),
            SimpleItemBean("CANCEL - ALL")
        )
    }

    override fun onItemClick(index: Int, item: SimpleItemBean) {
        when (index) {
            0 -> {
                addTask(Lifecycle.Event.ON_CREATE)
            }
            1 -> {
                addTask(Lifecycle.Event.ON_START)
            }
            2 -> {
                addTask(Lifecycle.Event.ON_RESUME)
            }
            3 -> {
                addTask(Lifecycle.Event.ON_PAUSE)
            }
            4 -> {
                addTask(Lifecycle.Event.ON_STOP)
            }
            5 -> {
                addTask(Lifecycle.Event.ON_DESTROY)
            }
            6 -> {
                getLifecycleTask().cancelLifecycleTask(Lifecycle.Event.ON_ANY)
            }
        }
    }

    // 创建任务
    private fun addTask(event: Lifecycle.Event) {
        val time = System.currentTimeMillis()
        getLifecycleTask().addLifecycleTask(event) {
            logE("event:$event time:${time}")
        }
    }
}