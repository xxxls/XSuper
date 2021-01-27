package com.xxxxls.module_base.util

import android.view.View
import androidx.annotation.LayoutRes
import com.xxxxls.module_base.R
import com.xxxxls.status.Status
import com.xxxxls.status.StatusFactory
import com.xxxxls.status.SuperStatusView

/**
 * 多状态
 * @author Max
 * @date 2019-12-16.
 */


/**
 * 配置多状态视图
 */
fun View.status(
    @LayoutRes loadingRes: Int = R.layout.base_status_loading,
    @LayoutRes emptyRes: Int = R.layout.base_status_empty,
    @LayoutRes errorRes: Int = R.layout.base_status_error,
    @LayoutRes noNetworkRes: Int = R.layout.base_status_no_network,
    //重试时是否自动切换loading状态
    isAutoSwitchLoading: Boolean = true,
    //重试回调，返回true时自动切换加载状态
    onRetry: (status: Status) -> Unit = { }
): SuperStatusView? {
    return StatusFactory.status(
        this,
        loadingRes,
        emptyRes,
        errorRes,
        noNetworkRes,
        isAutoSwitchLoading,
        onRetry
    )
}
