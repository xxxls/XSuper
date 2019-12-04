package com.xxxxls.module_user.ui.login

import com.xxxxls.module_base.net.BaseLiveData
import com.xxxxls.module_user.bean.UserBean
import com.xxxxls.module_user.net.UserApiRepository
import com.xxxxls.module_user.net.UserViewModel

/**
 * 登录VM
 * @author Max
 * @date 2019-11-29.
 */
class LoginViewModel : UserViewModel() {

    private val mUserRepository = createRepository<UserApiRepository>()

    val loginLiveData by lazy {
        BaseLiveData<UserBean>()
    }


    fun login(userName: String, password: String) {

        mUserRepository.requestApi(loginLiveData) {
            it.login(userName, password)
        }
    }


}