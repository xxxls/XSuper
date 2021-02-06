package com.xxxxls.example.ui.test

import com.alibaba.android.arouter.facade.annotation.Route
import com.xxxxls.example.bean.SimpleItemBean
import com.xxxxls.example.common.BaseListFragment
import com.xxxxls.module_base.constants.BasePaths
import com.xxxxls.module_base.constants.HomePaths
import com.xxxxls.module_base.util.jump
import com.xxxxls.titlebar.setTitleBarLeftText
import dagger.hilt.android.AndroidEntryPoint

/**
 * 测试相关
 * @author Max
 * @date 2020-02-06.
 */
@Route(path = HomePaths.HOME_FRAGMENT_TEST_INDEX)
@AndroidEntryPoint
class TestIndexFragment : BaseListFragment() {

    override fun onInitialize() {
        super.onInitialize()
        setTitleBarLeftText("返回") {
            activity?.onBackPressed()
        }
    }

    override fun getTitle(): String {
        return "Test"
    }

    override fun getItems(): Array<SimpleItemBean> {
        return arrayOf(
            SimpleItemBean("Flow")
        )
    }

    override fun onItemClick(index: Int, item: SimpleItemBean) {
        when (index) {
            0 -> {
                BasePaths.BASE_ACTIVITY_FRAGMENT.jump(BasePaths.KEY_ACTIVITY_FRAGMENT_PATH to HomePaths.HOME_FRAGMENT_FLOW_INDEX)
            }
        }
    }
}