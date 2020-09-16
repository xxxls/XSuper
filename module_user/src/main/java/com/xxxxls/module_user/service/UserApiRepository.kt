package com.xxxxls.module_user.service

import com.xxxxls.module_base.net.BaseApiRepository
import com.xxxxls.module_user.bean.UserBean
import com.xxxxls.module_user.service.db.UserDao
import com.xxxxls.module_user.service.db.UserDatabase
import com.xxxxls.xsuper.net.XSuperResult
import com.xxxxls.xsuper.net.engine.IHttpEngine

/**
 * 用户模块的基础请求Repository (ps:这里只是展示用法，如果某模块的baseUrl或网络公共参数不同可这样单独配置）
 * @author Max
 * @date 2019-11-28.
 */
class UserApiRepository : BaseApiRepository<UserApis>() {

    override fun createHttpEngine(): IHttpEngine {
        //可自定义配置该模块下的网络请求
        return super.createHttpEngine()
    }

    // 模拟多个ApiService
    private val api2: UserApis by lazy {
        createApi(UserApis::class.java)
    }

    // 用户数据库
    private val userDao: UserDao by lazy {
        UserDatabase.instance.getUserDao()
    }

    /**
     * 登录
     * @param userName 用户名
     * @param password 密码
     */
    suspend fun login(userName: String, password: String): XSuperResult<UserBean> {
//        api2.login(userName, password).enqueue()
        return api.login(userName, password).enqueue().apply {
            (this as? XSuperResult.Success<UserBean>)?.let {
                // 模拟保存用户信息
                saveLoginRecord(it.data!!)
            }
        }
    }

    /**
     * 保存登录记录
     */
    suspend fun saveLoginRecord(user: UserBean): Boolean {
        return try {
            userDao.insert(user)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    /**
     * 保存登录记录
     */
    suspend fun getAllLoginRecord(): List<UserBean>? {
        return try {
            userDao.getAll()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }


}