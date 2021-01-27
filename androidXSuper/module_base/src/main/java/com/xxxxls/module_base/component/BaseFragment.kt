package com.xxxxls.module_base.component

import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter
import com.xxxxls.module_base.util.ILog
import com.xxxxls.xsuper.component.SuperFragment
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * Base - Fragment
 * @author Max
 * @date 2019-11-26.
 */
open class BaseFragment : SuperFragment(), ILog {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ARouter.getInstance().inject(this)
    }

    override fun onInitialize() {
        super.onInitialize()
        registerEventBus()
    }

    override fun onDestroy() {
        super.onDestroy()
        unRegisterEventBus()
    }

    private fun registerEventBus() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }
    }

    private fun unRegisterEventBus() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this)
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvent(event: Object) {

    }
}