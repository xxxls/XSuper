package com.xxxxls.module_base.base

import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter
import com.xxxxls.xsuper.component.XSuperActivity
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * Base - Activity
 * @author Max
 * @date 2019-11-26.
 */
open class BaseActivity : XSuperActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        ARouter.getInstance().inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onInitialize() {
        super.onInitialize()
        registerEventBus()
    }

    override fun onDestroy() {
        super.onDestroy()
        unRegisterEventBus()
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onEvent(event: Object) {

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

}