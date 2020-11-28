package com.xxxxls.xsuper.component.bridge

import android.app.Activity
import android.app.Dialog

/**
 * 与组件的通信命令
 * @author Max
 * @date 2019-12-04.
 */
sealed class ComponentAction {

    /**
     * 展示加载弹窗
     */
    class ShowLoading(val id: Int? = null, val message: CharSequence? = null) : ComponentAction()

    /**
     * 关闭加载弹窗
     */
    class DismissLoading(val id: Int? = null) : ComponentAction()

    /**
     * 展示弹窗
     */
    class BuildDialog(val build: (activity: Activity?) -> Dialog?) : ComponentAction()

    /**
     * toast
     */
    class Toast(val message: CharSequence?) : ComponentAction()
}