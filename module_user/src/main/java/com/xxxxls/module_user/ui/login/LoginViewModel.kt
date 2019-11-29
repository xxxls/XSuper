package com.xxxxls.module_user.ui.login

import com.xxxxls.module_base.net.BaseViewModel
import com.xxxxls.module_user.net.UserApiRepository
import com.xxxxls.module_user.net.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

/**
 *
 * @author Max
 * @date 2019-11-29.
 */
class LoginViewModel : UserViewModel() {


    private val mJob = SupervisorJob()
    private val mScope = CoroutineScope(Dispatchers.IO + mJob)

    fun login(userName: String, password: String) {

        mRepository.apis().login(userName, password).await()
    }
}