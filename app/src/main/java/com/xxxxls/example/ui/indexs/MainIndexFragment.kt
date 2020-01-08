package com.xxxxls.example.ui.indexs

import com.alibaba.android.arouter.facade.annotation.Route
import com.xxxxls.example.bean.IndexItemBean
import com.xxxxls.module_base.constants.BasePaths
import com.xxxxls.module_base.constants.HomePaths
import com.xxxxls.module_base.constants.HomePaths.HOME_FRAGMENT_HOME_INDEXS
import com.xxxxls.module_base.util.jump

/**
 * 主页集
 * @author Max
 * @date 2020-01-07.
 */
@Route(path = HOME_FRAGMENT_HOME_INDEXS)
class MainIndexFragment : BaseIndexFragment() {
    override fun getTitle(): String {
        return "IndexS"
    }

    override fun getItems(): Array<IndexItemBean> {
        return arrayOf(IndexItemBean("tools"), IndexItemBean("widget"))
    }

    override fun onItemClick(index: Int, item: IndexItemBean) {
        when (index) {
            0 -> {
                BasePaths.BASE_ACTIVITY_FRAGMENT.jump(BasePaths.KEY_BASE_ACTIVITY_FRAGMENT_PATH to HomePaths.HOME_FRAGMENT_TOOLS_INDEX)
            }
            1 -> {
                BasePaths.BASE_ACTIVITY_FRAGMENT.jump(BasePaths.KEY_BASE_ACTIVITY_FRAGMENT_PATH to HomePaths.HOME_FRAGMENT_WIDGET_INDEX)
            }
        }
    }
}
