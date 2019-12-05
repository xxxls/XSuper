package com.xxxxls.module_user.ui.register

import com.alibaba.android.arouter.facade.annotation.Route
import com.xxxxls.module_base.base.BaseActivity
import com.xxxxls.module_base.constants.UserPaths
import com.xxxxls.module_user.R
import com.xxxxls.xsuper.net.viewmodel.ViewModelFactory
import com.xxxxls.xsuper.util.L
import com.xxxxls.xsuper.util.singleClick
import kotlinx.android.synthetic.main.user_activity_login.*
import kotlinx.android.synthetic.main.user_activity_login.et_password
import kotlinx.android.synthetic.main.user_activity_login.et_username
import kotlinx.android.synthetic.main.user_activity_register.*

/**
 * 用户-登录
 * @author Max
 * @date 2019-12-05.
 */
@Route(path = UserPaths.USER_ACTIVITY_REGISTER)
class RegisterActivity : BaseActivity() {

    private val mViewModel by ViewModelFactory(
        RegisterViewModel::class.java
    )

    override fun getLayoutResId(): Int {
        return R.layout.user_activity_register
    }

    override fun onInitialize() {
        super.onInitialize()
        mViewModel.registerLiveData.observe(this, success = {
            L.e("registerLiveData:$it")
        }, error = {
            L.e("registerLiveData:$it")
        })

        btn_register.singleClick {
            mViewModel.register(
                et_username.text.trim().toString(),
                et_password.text.trim().toString()
            )
        }
    }
}