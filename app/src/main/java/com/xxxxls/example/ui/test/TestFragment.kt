package com.xxxxls.example.ui.test

import android.content.Intent
import com.xxxxls.example.R
import com.xxxxls.example.ui.status.StatusActivity
import com.xxxxls.module_base.base.BaseFragment
import com.xxxxls.utils.singleClick
import kotlinx.android.synthetic.main.fragment_test.*

/**
 *
 * @author Max
 * @date 2019-12-16.
 */
class TestFragment : BaseFragment() {

    override fun getLayoutResId(): Int? {
        return R.layout.fragment_test
    }

    override fun onInitialize() {
        super.onInitialize()
        btn_status.singleClick {
            startActivity(Intent(context, StatusActivity::class.java))
        }
    }
}