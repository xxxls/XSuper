package com.xxxxls.module_user.ui.register

import com.xxxxls.module_user.bean.UserBean
import com.xxxxls.module_user.net.UserApis
import com.xxxxls.xsuper.net.viewmodel.FastApiViewModel
import com.xxxxls.xsuper.net.XSuperLiveData

/**
 * 注册VM
 * @author Max
 * @date 2019-11-29.
 */
class RegisterViewModel : FastApiViewModel<UserApis>() {

    val registerLiveData by lazy {
        XSuperLiveData<UserBean>()
    }

    fun register(userName: String, password: String) {

        requestApi(registerLiveData) {
            it.register(userName, password, password)
        }
    }


}