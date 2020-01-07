package com.xxxxls.example.ui.tools

import android.content.Intent
import com.alibaba.android.arouter.facade.annotation.Route
import com.xxxxls.example.bean.IndexItemBean
import com.xxxxls.example.ui.indexs.BaseIndexFragment
import com.xxxxls.example.ui.tools.glide.GlideActivity
import com.xxxxls.example.ui.tools.status.StatusActivity
import com.xxxxls.example.ui.tools.titlebar.TitleBarActivity
import com.xxxxls.module_base.constants.HomePaths

/**
 * 工具集
 * @author Max
 * @date 2020-01-02.
 */
@Route(path = HomePaths.HOME_FRAGMENT_TOOLS_INDEX)
class ToolsIndexFragment : BaseIndexFragment() {
    override fun getTitle(): String {
        return "Tools"
    }

    override fun getItems(): Array<IndexItemBean> {
        return arrayOf(IndexItemBean("status"), IndexItemBean("glide"), IndexItemBean("titlebar"))
    }

    override fun onItemClick(index: Int, item: IndexItemBean) {
        when (index) {
            0 -> {
                startActivity(Intent(context, StatusActivity::class.java))
            }
            1 -> {
                startActivity(Intent(context, GlideActivity::class.java))
            }
            2 -> {
                startActivity(Intent(context, TitleBarActivity::class.java))
            }
        }
    }
}