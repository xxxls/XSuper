package com.xxxxls.module_user.data

import com.xxxxls.module_base.net.response.BaseResponse
import com.xxxxls.module_user.bean.UserBean
import com.xxxxls.module_user.db.UserDao
import com.xxxxls.xsuper.exceptions.XSuperException
import com.xxxxls.xsuper.mvvm.DefaultResponseAdapter
import com.xxxxls.xsuper.mvvm.ResponseAdapter
import com.xxxxls.xsuper.model.XSuperResult
import com.xxxxls.xsuper.model.toFailureResult
import com.xxxxls.xsuper.model.toSuccessResult
import com.xxxxls.xsuper.repository.apiFlow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

/**
 * 用户模块Repository
 * @author Max
 * @date 2019-11-28.
 */
class UserRepository @Inject constructor(
    private val userApis: UserApis,
    private val userDao: UserDao
) {

    /**
     * 登录
     * @param userName 用户名
     * @param password 密码
     */
    suspend fun login(userName: String, password: String): Flow<XSuperResult<UserBean>> {
        val flow1 = flow<XSuperResult<UserBean>> {
            val result = userApis.login(userName, password)
            saveLoginRecord(result.data!!)
            emit(result.data!!.toSuccessResult())
        }.catch {
            emit(XSuperException(it).toFailureResult())
        }.flowOn(Dispatchers.IO)

        return apiFlow {
            userApis.login(userName, password)
        }
//        return flow<XSuperResult<UserBean>> {
//            val result = userApis.login(userName, password)
//            saveLoginRecord(result.data!!)
//            emit(result.data!!.toSuccessResult())
//        }.catch {
//            emit(XSuperException(it).toFailureResult())
//        }.flowOn(Dispatchers.IO)
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
    suspend fun getAllLoginRecord(): Flow<XSuperResult<List<UserBean>>> {
        return flow<XSuperResult<List<UserBean>>> {
            emit(userDao.getAll()!!.toSuccessResult())
        }.catch {
            emit(XSuperException(it).toFailureResult())
        }.flowOn(Dispatchers.IO)
    }
}
