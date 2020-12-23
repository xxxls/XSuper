package com.xxxxls.module_base.widget

import android.content.Context
import com.xxxxls.module_base.R
import com.xxxxls.xsuper.component.XSuperDialog

/**
 * 加载弹窗
 * @author Max
 * @date 2019-12-05.
 */
open class LoadingDialog(context: Context, val cancelable: Boolean = true) : XSuperDialog(context) {

    override fun onInitialize() {
        super.onInitialize()
        setCancelable(cancelable)
    }

    override fun getLayoutResId(): Int? {
        return R.layout.base_dialog_loading
    }
}