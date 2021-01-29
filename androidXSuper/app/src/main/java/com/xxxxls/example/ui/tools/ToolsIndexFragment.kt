package com.xxxxls.example.ui.tools

import android.content.Intent
import com.alibaba.android.arouter.facade.annotation.Route
import com.xxxxls.example.bean.SimpleItemBean
import com.xxxxls.example.common.BaseListFragment
import com.xxxxls.example.ui.tools.glide.GlideActivity
import com.xxxxls.example.ui.tools.status.StatusActivity
import com.xxxxls.example.ui.tools.titlebar.TitleBarActivity
import com.xxxxls.module_base.constants.BasePaths
import com.xxxxls.module_base.constants.HomePaths
import com.xxxxls.module_base.util.jump
import com.xxxxls.titlebar.setTitleBarLeftText
import com.xxxxls.module_base.constants.ExamplePaths.EXAMPLE_ACTIVITY_MULTI_VIDEO

/**
 * 工具集
 * @author Max
 * @date 2020-01-02.
 */
@Route(path = HomePaths.HOME_FRAGMENT_TOOLS_INDEX)
class ToolsIndexFragment : BaseListFragment() {

    override fun onInitialize() {
        super.onInitialize()
        setTitleBarLeftText("返回") {
            activity?.onBackPressed()
        }
    }

    override fun getTitle(): String {
        return "Tools"
    }

    override fun getItems(): Array<SimpleItemBean> {
        return arrayOf(
            SimpleItemBean("toolsList"),
            SimpleItemBean("status"),
            SimpleItemBean("glide"),
            SimpleItemBean("titleBar"),
            SimpleItemBean("logger"),
            SimpleItemBean("multiVideo"),
            SimpleItemBean("lifecycleTask"),
            SimpleItemBean("smartListRefresh"),
            SimpleItemBean("store")
        )
    }

    override fun onItemClick(index: Int, item: SimpleItemBean) {
        when (index) {
            0 -> {
                BasePaths.BASE_ACTIVITY_FRAGMENT.jump(BasePaths.KEY_ACTIVITY_FRAGMENT_PATH to HomePaths.HOME_FRAGMENT_TIMER_INDEX)
            }
            1 -> {
                startActivity(Intent(context, StatusActivity::class.java))
            }
            2 -> {
                startActivity(Intent(context, GlideActivity::class.java))
            }
            3 -> {
                startActivity(Intent(context, TitleBarActivity::class.java))
            }
            4 -> {
                BasePaths.BASE_ACTIVITY_FRAGMENT.jump(BasePaths.KEY_ACTIVITY_FRAGMENT_PATH to HomePaths.HOME_FRAGMENT_LOGGER_INDEX)
            }
            5 -> {
                EXAMPLE_ACTIVITY_MULTI_VIDEO.jump()
            }
            6 -> {
                BasePaths.BASE_ACTIVITY_FRAGMENT.jump(BasePaths.KEY_ACTIVITY_FRAGMENT_PATH to HomePaths.HOME_FRAGMENT_LIFECYCLE_TASK_INDEX)
            }
            7 -> {
                BasePaths.BASE_ACTIVITY_FRAGMENT.jump(BasePaths.KEY_ACTIVITY_FRAGMENT_PATH to HomePaths.HOME_FRAGMENT_LIST_REFRESH_INDEX)
            }
            8 -> {
                BasePaths.BASE_ACTIVITY_FRAGMENT.jump(BasePaths.KEY_ACTIVITY_FRAGMENT_PATH to HomePaths.HOME_FRAGMENT_STORE_INDEX)
            }
        }
    }
}