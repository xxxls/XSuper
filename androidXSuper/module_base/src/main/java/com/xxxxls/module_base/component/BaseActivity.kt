package com.xxxxls.module_base.component

import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter
import com.xxxxls.module_base.util.ILog
import com.xxxxls.module_base.widget.LoadingDialog
import com.xxxxls.xsuper.component.SuperActivity
import com.xxxxls.xsuper.component.bridge.ComponentAction
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

/**
 * Base - Activity
 * @author Max
 * @date 2019-11-26.
 */
open class BaseActivity : SuperActivity(), ILog {

    private var mLoadingDialog: LoadingDialog? = null

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
        dismissLoading()
        mLoadingDialog = null
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

    override fun showLoading(id: Int?, message: CharSequence?) {
        super.showLoading(id, message)
        if (mLoadingDialog == null) {
            mLoadingDialog = LoadingDialog(getContext())
        }

        if (mLoadingDialog!!.isShowing) {
            return
        }
        mLoadingDialog!!.show()
    }

    override fun dismissLoading(id: Int?) {
        super.dismissLoading(id)
        if (mLoadingDialog == null || !mLoadingDialog!!.isShowing) {
            return
        }
        mLoadingDialog!!.dismiss()
    }

    override fun onAction(action: ComponentAction) {
        super.onAction(action)
        when (action) {
            is FinishAction -> {
                // 结束当前页面命令
                finish()
            }
        }
    }
}