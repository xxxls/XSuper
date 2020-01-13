package com.xxxxls.example.ui.tools

import android.view.View
import android.widget.FrameLayout
import android.widget.LinearLayout
import com.alibaba.android.arouter.facade.annotation.Route
import com.xxxxls.example.R
import com.xxxxls.module_base.base.BaseFragment
import com.xxxxls.module_base.constants.HomePaths
import com.xxxxls.module_base.constants.HomePaths.HOME_FRAGMENT_TOOLS_LIST
import com.xxxxls.module_base.util.newFragment
import com.xxxxls.utils.ktx.showFragment
import kotlinx.android.synthetic.main.fragment_tools_list.*

/**
 * 工具列表
 * @author Max
 * @date 2020-01-13.
 */
@Route(path = HOME_FRAGMENT_TOOLS_LIST)
class ToolsListFragment : BaseFragment() {

    override fun getLayoutResId(): Int? {
        return R.layout.fragment_tools_list
    }

    override fun onInitialize() {
        super.onInitialize()
        addFragment(HomePaths.HOME_FRAGMENT_TIMER_INDEX)
    }

    /**
     * 添加fragment
     */
    private fun addFragment(path: String) {
        val fragment = path.newFragment() ?: return

        val frameLayout = FrameLayout(context!!)
        frameLayout.id = View.generateViewId()
        showFragment(fragment = fragment, containerViewId = frameLayout.id)

        layout_content.addView(
            frameLayout,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        )
    }

}