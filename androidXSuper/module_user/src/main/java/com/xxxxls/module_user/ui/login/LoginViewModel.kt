package com.xxxxls.module_user.ui.login

import androidx.hilt.lifecycle.ViewModelInject
import com.xxxxls.module_base.mvvm.BaseLiveData
import com.xxxxls.module_base.mvvm.BaseViewModel
import com.xxxxls.module_user.bean.LoginBean
import com.xxxxls.module_user.bean.UserBean
import com.xxxxls.module_user.data.UserRepository
import com.xxxxls.xsuper.model.*
import kotlinx.coroutines.flow.*

/**
 * 登录VM
 * @author Max
 * @date 2019-11-29.
 */
class LoginViewModel @ViewModelInject constructor(
    private val userRepository: UserRepository
) : BaseViewModel() {

    // 登录信息
    val loginLiveData by lazy {
        BaseLiveData<LoginBean>()
    }

    // 用户信息
    val userLiveData by lazy {
        BaseLiveData<UserBean>()
    }

    /**
     * 登录（只登录）（方式一：Repository直接返回的响应体）
     */
    fun login(userName: String, password: String) {
        launchF(loginLiveData) {
            userRepository.login(userName, password)
        }
    }

    /**
     * 登录（只登录）（方式二：Repository返回的是响应体的包装格式/Result）
     */
    fun login2(userName: String, password: String) {
        launchFR(loginLiveData) {
            userRepository.login2(userName, password)
        }
    }

    /**
     * 登录+获取用户信息（方式一：在ViewModel做接口合并）
     */
    fun loginAndGetUserInfo(userName: String, password: String) {
        launchF(userLiveData) {
            userRepository.login(userName, password).flatMapConcat {
                logE("loginAndGetUserInfo - flatMapConcat login:$it")
                userRepository.getUserInfo(userName, password + "11")
            }
        }
    }

    /**
     * 登录+获取用户信息 （方式二：在Repository做接口合并）
     */
    fun loginAndGetUserInfo2(userName: String, password: String) {
        launchF(userLiveData) {
            userRepository.loginAndGetUserInfo(userName, password)
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