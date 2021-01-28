package com.xxxxls.module_base.refresh

import com.xxxxls.module_base.constants.Constants.FIRST_PAGE_INDEX


/**
 * 常用分页配置
 * @author Max
 * @date 1/28/21.
 */
class CommonSmartRefreshConfig {
    companion object {
        val instance: SmartListRefreshHelper.Config by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            SmartListRefreshHelper.Config(FIRST_PAGE_INDEX, 3)
        }
    }
}