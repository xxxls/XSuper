package com.xxxxls.module_user.ui.register

import com.xxxxls.module_base.net.BaseLiveData
import com.xxxxls.module_user.bean.UserBean
import com.xxxxls.module_user.service.UserApis
import com.xxxxls.module_base.net.FastApiViewModel

/**
 * 注册VM
 * @author Max
 * @date 2019-12-05.
 */
class RegisterViewModel : FastApiViewModel<UserApis>() {

    val registerLiveData by lazy {
        BaseLiveData<UserBean>()
    }

    fun register(userName: String, password: String) {
        requestApi(registerLiveData) {
            it.register(userName, password, password)
        }
    }

}