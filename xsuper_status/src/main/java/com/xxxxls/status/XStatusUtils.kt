package com.xxxxls.status

import android.view.View
import androidx.annotation.LayoutRes

/**
 *
 * @author Max
 * @date 2019-12-16.
 */


/**
 * 配置多状态
 */
fun View.statusConfig(
    @LayoutRes loadingRes: Int = View.NO_ID,
    @LayoutRes emptyRes: Int = View.NO_ID,
    @LayoutRes errorRes: Int = View.NO_ID,
    @LayoutRes noNetwork: Int = View.NO_ID,
    onRetry: (statusView: IStatusView, status: XStatus) -> Unit = { _, _ -> Unit }
) {

}