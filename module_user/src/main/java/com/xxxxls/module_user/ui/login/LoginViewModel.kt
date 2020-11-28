package com.xxxxls.module_user.ui.login

import androidx.hilt.lifecycle.ViewModelInject
import com.xxxxls.module_base.mvvm.BaseLiveData
import com.xxxxls.module_base.mvvm.BaseViewModel
import com.xxxxls.module_user.bean.UserBean
import com.xxxxls.module_user.data.UserRepository
import com.xxxxls.xsuper.model.*
import kotlinx.coroutines.flow.collectLatest

/**
 * 登录VM
 * @author Max
 * @date 2019-11-29.
 */
class LoginViewModel @ViewModelInject constructor(
    private val userRepository: UserRepository
) : BaseViewModel() {

    val loginLiveData by lazy {
        BaseLiveData<UserBean>()
    }

    /**
     * 登录
     */
    fun login(userName: String, password: String) {
        // 模拟获取本地记录
        getLoginRecord()

        launchL(loginLiveData, null) {
            userRepository.login(userName, password)
        }
    }

    /**
     * 获取登录记录
     */
    fun getLoginRecord() {
        launch(loading = null) {
            userRepository.getLoginRecord().collectLatest {
                it.doSuccess {
                    logD("getLoginRecord() old-count:${it.size}")
                    it.forEachIndexed { index, userBean ->
                        logD("index:$index,user:$userBean")
                    }
                }.doFailure {
                    logE("getLoginRecord() it:${it}")
                }
            }
        }
    }
}