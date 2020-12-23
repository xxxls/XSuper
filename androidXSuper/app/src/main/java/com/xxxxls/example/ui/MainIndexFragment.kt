package com.xxxxls.example.ui

import com.alibaba.android.arouter.facade.annotation.Route
import com.xxxxls.example.bean.SimpleItemBean
import com.xxxxls.example.common.BaseListFragment
import com.xxxxls.module_base.constants.BasePaths
import com.xxxxls.module_base.constants.HomePaths
import com.xxxxls.module_base.constants.HomePaths.HOME_FRAGMENT_HOME_INDEXS
import com.xxxxls.module_base.util.jump
import dagger.hilt.android.AndroidEntryPoint

/**
 * 主页集
 * @author Max
 * @date 2020-01-07.
 */
@Route(path = HOME_FRAGMENT_HOME_INDEXS)
class MainIndexFragment : BaseListFragment() {
    override fun getTitle(): String {
        return "IndexS"
    }

    override fun getItems(): Array<SimpleItemBean> {
        return arrayOf(SimpleItemBean("tools"), SimpleItemBean("widget"), SimpleItemBean("network"))
    }

    override fun onItemClick(index: Int, item: SimpleItemBean) {
        when (index) {
            0 -> {
                BasePaths.BASE_ACTIVITY_FRAGMENT.jump(BasePaths.KEY_ACTIVITY_FRAGMENT_PATH to HomePaths.HOME_FRAGMENT_TOOLS_INDEX)
            }
            1 -> {
                BasePaths.BASE_ACTIVITY_FRAGMENT.jump(BasePaths.KEY_ACTIVITY_FRAGMENT_PATH to HomePaths.HOME_FRAGMENT_WIDGET_INDEX)
            }
            2 -> {
                BasePaths.BASE_ACTIVITY_FRAGMENT.jump(BasePaths.KEY_ACTIVITY_FRAGMENT_PATH to HomePaths.HOME_FRAGMENT_NETWORK_INDEX)
            }
        }
    }
}
