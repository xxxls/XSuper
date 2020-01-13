package com.xxxxls.example.ui.tools.titlebar

import android.view.View
import com.xxxxls.example.R
import com.xxxxls.module_base.base.BaseActivity
import com.xxxxls.titlebar.OnLeftTitleClickListener
import com.xxxxls.titlebar.OnTitleBarClickListener
import com.xxxxls.utils.ktx.toast
import kotlinx.android.synthetic.main.activity_titlebar.*

/**
 * 标题栏
 * @author Max
 * @date 2020-01-03.
 */
class TitleBarActivity : BaseActivity(), OnTitleBarClickListener {

    override fun getLayoutResId(): Int {
        return R.layout.activity_titlebar
    }

    override fun onInitialize() {
        super.onInitialize()

        titlebar_a.onLeftTitleClickListener = object : OnLeftTitleClickListener {
            override fun onLeftTitleClick(view: View) {
                onBackPressed()
            }
        }

        titlebar_b.setOnTitleBarClickListener(this)

        titlebar_c.setOnTitleBarClickListener(this)

        titlebar_d.setOnTitleBarClickListener(this)

        titlebar_e.setOnTitleBarClickListener(this)
    }

    override fun onTitleClick(view: View) {
        toast("click title")
    }

    override fun onSubTitleClick(view: View) {
        toast("click subTitle")
    }

    override fun onLeftTitleClick(view: View) {
        toast("click left")
    }

    override fun onRightTitleClick(view: View) {
        toast("click right")
    }
}