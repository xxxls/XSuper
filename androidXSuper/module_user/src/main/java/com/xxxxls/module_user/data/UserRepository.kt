package com.xxxxls.module_user.data

import com.xxxxls.module_base.mvvm.BaseRepository
import com.xxxxls.module_user.bean.LoginBean
import com.xxxxls.module_user.bean.UserBean
import com.xxxxls.module_user.db.UserDao
import com.xxxxls.xsuper.exceptions.ApiException
import com.xxxxls.xsuper.model.XSuperResult
import kotlinx.coroutines.flow.*
import javax.inject.Inject

/**
 * 用户模块Repository
 * @author Max
 * @date 2019-11-28.
 */
class UserRepository @Inject constructor(
    private val userApis: UserApis,
    private val userDao: UserDao
) : BaseRepository() {

    /**
     * 注册账号
     * @param userName 用户名
     * @param password 密码
     */
    suspend fun register(userName: String, password: String) = flowApi {
        userApis.register(userName, password, password)
    }

    /**
     * 登录  (方式一：直接响应数据体)
     * @param userName 用户名
     * @param password 密码
     */
    suspend fun login(userName: String, password: String): Flow<LoginBean> {
        return flowApi {
            // 请求接口获取到结果
            userApis.login(userName, password)
        }
    }

    /**
     * 登录 (方式二：响应Result包装格式)
     * @param userName 用户名
     * @param password 密码
     */
    suspend fun login2(userName: String, password: String): Flow<XSuperResult<LoginBean>> {
        return flowApiResult { userApis.login(userName, password) }
    }

    /**
     * 获取用户信息
     * @param userName 用户名
     * @param password 密码
     *
     * TODO 有的公司是先登录，登录会返回token，然后通过token再请求用户信息，
     * TODO 由于这里采用的WanAndroid提供的api，它不是这样的，所有这里继续调用登录接口来做示例。
     */
    suspend fun getUserInfo(userName: String, password: String): Flow<UserBean> {
        return flowApi {
            // 请求接口获取到结果
            userApis.getUserInfo(userName, password)
        }
    }

    /**
     * 登录+获取用户新增
     * @param userName 用户名
     * @param password 密码
     */
    suspend fun loginAndGetUserInfo(userName: String, password: String): Flow<UserBean> {
        return flowApi {
            // 请求接口获取到结果
            val loginInfo = userApis.login(userName, password)
            if (loginInfo.isSuccess()) {
                // 成功后请求获取用户信息接口
                // 正常情况下：可能是依赖登录接口返回的token，再进行登录接口调用，这里只是做个示例。
                val userInfo = userApis.getUserInfo(userName, password)
                // 这里模拟一下：保存本地记录
                saveLoginRecord(userInfo.getBody()!!).collect()
                return@flowApi userInfo
            } else {
                // 异常直接抛出去
                throw ApiException(loginInfo)
            }
        }
    }

    /**
     * 保存登录记录
     */
    fun saveLoginRecord(user: UserBean): Flow<Boolean> {
        return flowSafety {
            userDao.insert(user)
            true
        }
    }

    /**
     * 获取登录记录
     */
    fun getLoginRecord(): Flow<XSuperResult<List<UserBean>>> {
        return flowResult {
            userDao.getAll() ?: emptyList()
        }
    }
}
