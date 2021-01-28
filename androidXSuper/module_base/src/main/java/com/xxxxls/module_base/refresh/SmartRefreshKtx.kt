package com.xxxxls.module_base.refresh

import android.view.View
import androidx.lifecycle.LifecycleOwner
import com.xxxxls.module_base.util.status

/**
 * 构建智能刷新
 * @param lifecycleOwner 页面生命周期
 * @param source 数据源
 * @param config 配置
 */
fun <T> SmartListRefreshHelper.IView<T>.buildSmartRefresh(
    source: SmartListRefreshHelper.ISource<T>,
    lifecycleOwner: LifecycleOwner = this as LifecycleOwner,
    config: SmartListRefreshHelper.Config = CommonSmartRefreshConfig.instance
): SmartListRefreshHelper<T> {
    return SmartListRefreshHelper(lifecycleOwner, this, source, config).apply {
        (this@buildSmartRefresh.getRefreshLayout() as? View)?.let {
            // 配置状态
            it.status(isAutoSwitchLoading = true) {
                refresh()
            }
            // 启动状态
            enableStatus()
        }
    }
}
