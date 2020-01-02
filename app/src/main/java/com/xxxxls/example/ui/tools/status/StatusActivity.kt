package com.xxxxls.example.ui.tools.status

import androidx.fragment.app.Fragment
import com.xxxxls.example.R
import com.xxxxls.module_base.base.BaseActivity
import com.xxxxls.xsuper.adapter.CommonFragmentPagerAdapter
import kotlinx.android.synthetic.main.activity_status.*

/**
 * 多状态视图
 * @author Max
 * @date 2019-12-16.
 */
class StatusActivity : BaseActivity() {

    override fun getLayoutResId(): Int {
        return R.layout.activity_status
    }

    override fun onInitialize() {
        super.onInitialize()
        initView()
    }

    private fun initView() {
        val fragment = arrayListOf<Fragment>(StatusXmlFragment(), StatusCodeFragment())
        val titles = arrayListOf<String>("XML", "CODE")
        viewPager.adapter = CommonFragmentPagerAdapter(supportFragmentManager, fragment, titles)
        tabLayout.setupWithViewPager(viewPager)
    }

}