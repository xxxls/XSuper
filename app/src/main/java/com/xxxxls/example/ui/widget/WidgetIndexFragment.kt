package com.xxxxls.example.ui.widget

import android.content.Intent
import com.alibaba.android.arouter.facade.annotation.Route
import com.xxxxls.example.bean.SimpleItemBean
import com.xxxxls.example.common.BaseListFragment
import com.xxxxls.module_base.constants.HomePaths
import com.xxxxls.titlebar.setTitleBarLeftText

/**
 * Widget
 * @author Max
 * @date 2020-01-07.
 */
@Route(path = HomePaths.HOME_FRAGMENT_WIDGET_INDEX)
class WidgetIndexFragment : BaseListFragment() {

    override fun onInitialize() {
        super.onInitialize()
        setTitleBarLeftText("返回") {
            activity?.onBackPressed()
        }
    }
    override fun getTitle(): String {
        return "Widget"
    }

    override fun getItems(): Array<SimpleItemBean> {
        return arrayOf(SimpleItemBean("semicircleProgress"))
    }

    override fun onItemClick(index: Int, item: SimpleItemBean) {
        when (index) {
            0 -> {
                startActivity(Intent(context, SemicircleProgressActivity::class.java))
            }
        }
    }
}