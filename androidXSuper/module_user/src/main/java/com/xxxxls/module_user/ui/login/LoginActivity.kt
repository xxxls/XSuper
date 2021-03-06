package com.xxxxls.module_user.ui.login

import com.alibaba.android.arouter.facade.annotation.Route
import com.xxxxls.module_base.component.BaseActivity
import com.xxxxls.module_base.constants.UserPaths
import com.xxxxls.module_user.R
import com.xxxxls.utils.ktx.singleClick
import com.xxxxls.xsuper.viewmodel.superViewModels
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.user_activity_login.*

/**
 * 用户-登录
 * @author Max
 * @date 2019-11-28.
 */
@Route(path = UserPaths.USER_ACTIVITY_LOGIN)
@AndroidEntryPoint
class LoginActivity : BaseActivity() {

    private val mViewModel: LoginViewModel by superViewModels()

    override fun getLayoutResId(): Int {
        return R.layout.user_activity_login
    }

    override fun onInitialize() {
        super.onInitialize()

        btn_login.singleClick {
            mViewModel.login(et_username.text.trim().toString(), et_password.text.trim().toString())
        }
    }

    override fun onInitObserve() {
        super.onInitObserve()
        mViewModel.loginLiveData.observe(success = {
            com.xxxxls.utils.L.e("loginLiveData:$it")
        }, failure = {

            com.xxxxls.utils.L.e("loginLiveData:$it")
        })
    }

}