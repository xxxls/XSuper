package com.xxxxls.example.ui.tools

import android.content.Intent
import com.xxxxls.example.R
import com.xxxxls.example.ui.tools.glide.GlideActivity
import com.xxxxls.example.ui.tools.status.StatusActivity
import com.xxxxls.module_base.base.BaseFragment
import com.xxxxls.utils.singleClick
import kotlinx.android.synthetic.main.fragment_tools.*

/**
 * 工具集
 * @author Max
 * @date 2020-01-02.
 */
class ToolsIndexFragment : BaseFragment() {

    override fun getLayoutResId(): Int? {
        return R.layout.fragment_tools
    }

    override fun onInitialize() {
        super.onInitialize()
        btn_status.singleClick {
            startActivity(Intent(context, StatusActivity::class.java))
        }

        btn_glide.singleClick {
            startActivity(Intent(context, GlideActivity::class.java))
        }
    }
}