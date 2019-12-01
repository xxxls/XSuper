package com.xxxxls.module_user.ui.login

import com.xxxxls.module_user.bean.UserBean
import com.xxxxls.module_user.net.UserApiRepository
import com.xxxxls.module_user.net.UserViewModel
import com.xxxxls.xsuper.net.XSuperLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

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
    private val mJob = SupervisorJob()
    private val mScope = CoroutineScope(Dispatchers.IO + mJob)

    fun login(userName: String, password: String) {

        mScope.launch {
            //            val result = mRepository.apis().login(userName, password).await()
//            L.e(message = "result:$result")

            mRepository.requestApi(loginLiveData) {
                it.login(userName, password)
            }
        }

        val num1 = mUserRepository.login("", "")

        val num2 = mUserRepository.login("", "")

        val num5 = mRepository.login("", "")

        val num6 = mRepository.login("", "")
    }


}