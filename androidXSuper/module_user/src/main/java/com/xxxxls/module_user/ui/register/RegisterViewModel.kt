package com.xxxxls.module_user.ui.register

import androidx.hilt.lifecycle.ViewModelInject
import com.xxxxls.module_base.mvvm.BaseLiveData
import com.xxxxls.module_base.mvvm.BaseViewModel
import com.xxxxls.module_user.bean.UserBean
import com.xxxxls.module_user.data.UserRepository

/**
 * 注册VM
 * @author Max
 * @date 2019-12-05.
 */
class RegisterViewModel @ViewModelInject constructor(
    private val userRepository: UserRepository
) : BaseViewModel() {

    val registerLiveData by lazy {
        BaseLiveData<UserBean>()
    }

    fun register(userName: String, password: String) {
        launchF(registerLiveData){
            userRepository.register(userName, password)
        }
    }

}