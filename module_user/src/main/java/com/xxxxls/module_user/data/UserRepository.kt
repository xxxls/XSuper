package com.xxxxls.module_user.data

import com.xxxxls.module_base.mvvm.BaseRepository
import com.xxxxls.module_user.bean.UserBean
import com.xxxxls.module_user.db.UserDao
import com.xxxxls.xsuper.adapter.ExceptionAnalyzer
import com.xxxxls.xsuper.exceptions.XSuperException
import com.xxxxls.xsuper.model.XSuperResult
import com.xxxxls.xsuper.model.toFailureResult
import com.xxxxls.xsuper.model.toSuccessResult
import kotlinx.coroutines.Dispatchers
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
     * 登录
     * @param userName 用户名
     * @param password 密码
     */
    suspend fun login(userName: String, password: String): Flow<XSuperResult<UserBean>> {
        return apiFlow {
            // 请求接口获取到结果
            val result = userApis.login(userName, password)
            // 模拟保存到本地记录
            saveLoginRecord(result.data!!)
            // 返回出去
            result
        }
    }

    /**
     * 保存登录记录
     */
    private suspend fun saveLoginRecord(user: UserBean): Boolean {
        return try {
            userDao.insert(user)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

    /**
     * 获取登录记录
     */
    suspend fun getLoginRecord(): Flow<XSuperResult<List<UserBean>>> {
        return flow<XSuperResult<List<UserBean>>> {
            emit(userDao.getAll()!!.toSuccessResult())
        }.catch {
            emit(XSuperException(it).toFailureResult())
        }.flowOn(Dispatchers.IO)
    }

    /**
     * 注册账号
     * @param userName 用户名
     * @param password 密码
     */
    suspend fun register(userName: String, password: String) =
        apiFlow {
            userApis.register(userName, password, password)
        }
}
