package com.xxxxls.module_user.ui.register

import com.alibaba.android.arouter.facade.annotation.Route
import com.xxxxls.module_base.base.BaseActivity
import com.xxxxls.module_base.constants.UserPaths
import com.xxxxls.module_user.R
import com.xxxxls.xsuper.net.viewmodel.ViewModelFactory
import com.xxxxls.utils.ktx.singleClick
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

    override fun onInitObserve() {
        super.onInitObserve()
        mViewModel.registerLiveData.observe(this, success = {
            com.xxxxls.utils.L.e("registerLiveData:$it")
        }, error = {
            com.xxxxls.utils.L.e("registerLiveData:$it")
        })
    }

    override fun onInitialize() {
        super.onInitialize()

        btn_register.singleClick {
            mViewModel.register(
                et_username.text.trim().toString(),
                et_password.text.trim().toString()
            )
        }
    }
}