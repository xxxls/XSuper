package com.xxxxls.module_user.ui.login

import androidx.lifecycle.viewModelScope
import com.xxxxls.module_user.bean.UserBean
import com.xxxxls.module_user.net.UserApiRepository
import com.xxxxls.module_user.net.UserViewModel
import com.xxxxls.xsuper.net.XSuperLiveData
import kotlinx.coroutines.*

/**
 * 登录VM
 * @author Max
 * @date 2019-11-29.
 */
class LoginViewModel : UserViewModel() {

    private val mUserRepository = createRepository<UserApiRepository>()

    val loginLiveData by lazy {
        XSuperLiveData<UserBean>()
    }


    fun login(userName: String, password: String) {

        mUserRepository.requestApi(loginLiveData) {
            it.login(userName, password)
        }

        val num1 = mUserRepository.login("", "")

        val num2 = mUserRepository.login("", "")
    }


}