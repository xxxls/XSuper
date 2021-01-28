package com.xxxxls.module_base.util

import android.content.Context
import com.xxxxls.utils.ktx.toast

/**
 * 异常-帮助类
 * @author Max
 * @date 1/28/21.
 */


/**
 * 分析异常并toast提示
 * @param context 上下文
 */
fun Throwable?.showToast(context: Context? = null) {
    if (this == null) return
    // TODO 待完善
    val toastMessage = this.message
    context?.toast(toastMessage) ?: toast(toastMessage)
}


