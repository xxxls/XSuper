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

    private val userRepository: UserApiRepository by lazy {
        createRepository(UserApiRepository::class.java)
    }

    // 模拟多Repository的情况
    private val userApiRepository2: UserApiRepository by lazy {
        createRepository(UserApiRepository::class.java)
    }

    val loginLiveData by lazy {
        BaseLiveData<UserBean>()
    }

    /**
     * 登录 方案一
     */
    fun login(userName: String, password: String) {
        // 模拟获取本地记录
        getAllLoginRecord()
        launch(loading = null) {
            userRepository.login(userName, password).callback(loginLiveData)
        }
    }

    /**
     * 登录 方案一
     */
    fun login2(userName: String, password: String) {
        launch {
            userRepository.requestApi {
                it.login(userName, password)
            }.callback(loginLiveData)
        }
    }

    /**
     * 获取登录记录
     */
    fun getAllLoginRecord() {
        launch(loading = null) {
            logE("getAllLoginRecord()")
            userRepository.getAllLoginRecord()?.let {
                logE("getAllLoginRecord() old-count:${it.size}")
                it.forEachIndexed { index, userBean ->
                    logE("index:$index,user:$userBean")
                }
            }
        }
    }
}