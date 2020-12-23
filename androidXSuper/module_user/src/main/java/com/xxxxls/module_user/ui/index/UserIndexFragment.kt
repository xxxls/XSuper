package com.xxxxls.module_user.ui.index

import com.alibaba.android.arouter.facade.annotation.Route
import com.xxxxls.module_base.component.BaseFragment
import com.xxxxls.module_base.constants.UserPaths
import com.xxxxls.module_base.util.jump
import com.xxxxls.module_user.R
import com.xxxxls.utils.ktx.singleClick
import kotlinx.android.synthetic.main.fragment_user_index.*

/**
 * 用户主页
 * @author Max
 * @date 2019-12-07.
 */
@Route(path = UserPaths.USER_FRAGMENT_INDEX)
class UserIndexFragment : BaseFragment() {

    override fun getLayoutResId(): Int? {
        return R.layout.fragment_user_index
    }

    override fun onInitialize() {
        super.onInitialize()
        tv_login.singleClick {
            UserPaths.USER_ACTIVITY_LOGIN.jump()
        }

        tv_register.singleClick {
            UserPaths.USER_ACTIVITY_REGISTER.jump()
        }
    }
}