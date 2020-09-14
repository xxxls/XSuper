package com.xxxxls.module_user.ui.login

import com.xxxxls.module_base.net.BaseLiveData
import com.xxxxls.module_user.bean.UserBean
import com.xxxxls.module_user.service.UserApiRepository
import com.xxxxls.module_user.service.UserViewModel
import com.xxxxls.xsuper.net.callback

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

    /**
     * 登录 方案一
     */
    fun login(userName: String, password: String) {
        launch(loading = null) {
            mUserRepository.login(userName, password).callback(loginLiveData)
        }
    }

    /**
     * 登录 方案一
     */
    fun login2(userName: String, password: String) {
        launch {
            mUserRepository.requestApi {
                it.login(userName, password)
            }.callback(loginLiveData)
        }
    }

}