package com.xxxxls.example

import com.xxxxls.module_base.base.BaseActivity
import com.xxxxls.module_base.constants.UserPaths
import com.xxxxls.module_base.util.jump
import com.xxxxls.xsuper.util.singleClick
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun getLayoutResId(): Int {
        return R.layout.activity_main
    }

    override fun onInitialize() {
        super.onInitialize()
        tv_login.singleClick {
            UserPaths.USER_ACTIVITY_LOGIN.jump()
        }
    }
}
