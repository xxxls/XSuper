package com.xxxxls.module_user.ui.login

import androidx.lifecycle.ViewModelProvider
import com.alibaba.android.arouter.facade.annotation.Route
import com.xxxxls.module_base.base.BaseActivity
import com.xxxxls.module_base.constants.UserPaths
import com.xxxxls.module_user.R
import com.xxxxls.xsuper.util.singleClick
import kotlinx.android.synthetic.main.user_activity_login.*

/**
 * 用户-登录
 * @author Max
 * @date 2019-11-28.
 */
@Route(path = UserPaths.USER_ACTIVITY_LOGIN)
class LoginActivity : BaseActivity() {

    val mViewModel: LoginViewModel by lazy {
        ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    override fun getLayoutResId(): Int {
        return R.layout.user_activity_login
    }

    override fun onInitialize() {
        super.onInitialize()

        btn_login.singleClick {
            mViewModel.login(et_username.text.trim().toString(), et_password.text.trim().toString())
        }
    }
}