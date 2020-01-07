package com.xxxxls.example.ui.widget

import android.content.Intent
import com.alibaba.android.arouter.facade.annotation.Route
import com.xxxxls.example.bean.IndexItemBean
import com.xxxxls.example.ui.indexs.BaseIndexFragment
import com.xxxxls.module_base.constants.HomePaths

/**
 * Widget
 * @author Max
 * @date 2020-01-07.
 */
@Route(path = HomePaths.HOME_FRAGMENT_WIDGET_INDEX)
class WidgetIndexFragment : BaseIndexFragment() {

    override fun getTitle(): String {
        return "Widget"
    }

    override fun getItems(): Array<IndexItemBean> {
        return arrayOf(IndexItemBean("semicircleProgress"))
    }

    override fun onItemClick(index: Int, item: IndexItemBean) {
        when (index) {
            0 -> {
                startActivity(Intent(context, SemicircleProgressActivity::class.java))
            }
        }
    }
}