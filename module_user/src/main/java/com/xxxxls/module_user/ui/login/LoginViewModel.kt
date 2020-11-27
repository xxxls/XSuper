package com.xxxxls.module_user.ui.login

import androidx.hilt.lifecycle.ViewModelInject
import com.xxxxls.module_base.net.BaseLiveData
import com.xxxxls.module_base.net.BaseViewModel
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
        getAllLoginRecord()
        launch(loading = null) {
            userRepository.login(userName, password).collectLatest {
                loginLiveData.postValue(it)
            }
        }
    }

    /**
     * 获取登录记录
     */
    fun getAllLoginRecord() {
        launch(loading = null) {
            logD("getAllLoginRecord()")
            userRepository.getAllLoginRecord().collectLatest {
                it.doSuccess {
                    logD("getAllLoginRecord() old-count:${it.size}")
                    it.forEachIndexed { index, userBean ->
                        logD("index:$index,user:$userBean")
                    }
                }.doFailure {
                    logE("getAllLoginRecord() it:${it}")
                }
            }
        }
    }
}