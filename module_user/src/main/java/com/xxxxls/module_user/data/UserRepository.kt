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
     * 登录  (方式一)
     * @param userName 用户名
     * @param password 密码
     */
    suspend fun login(userName: String, password: String): Flow<XSuperResult<UserBean>> {
        return apiFlow {
            // 请求接口获取到结果
            val result = userApis.login(userName, password)
            // 模拟保存到本地记录
            saveLoginRecord(result.data!!).collectLatest {
                logE("saveLoginRecord() $it")
            }
            // 返回出去
            return@apiFlow result
        }
    }

    /**
     * 登录 (方式二)
     * @param userName 用户名
     * @param password 密码
     */
    suspend fun login2(userName: String, password: String): Flow<XSuperResult<UserBean>> {
        return userApis.login(userName, password).asApiFlow()
    }

    /**
     * 登录 (方式三)
     * @param userName 用户名
     * @param password 密码
     */
    suspend fun login3(userName: String, password: String): Flow<XSuperResult<UserBean>> {
        return resultFlow {
            userApis.login(userName, password).data!!
        }
    }

    /**
     * 登录 (方式四) (等同与方式三)
     * @param userName 用户名
     * @param password 密码
     */
    suspend fun login4(userName: String, password: String): Flow<XSuperResult<UserBean>> {
        return flow {
            emit(userApis.login(userName, password).asApiResult())
        }.catch {
            emit(XSuperException(it).toFailureResult())
        }.flowOn(Dispatchers.IO)
    }

    /**
     * 登录 (方式五)
     * @param userName 用户名
     * @param password 密码
     */
    suspend fun login5(userName: String, password: String): Flow<XSuperResult<UserBean>> {
        return flow {
            emit(apiResult {
                userApis.login(userName, password)
            })
        }.catch {
            emit(XSuperException(it).toFailureResult())
        }.flowOn(Dispatchers.IO)
    }

    /**
     * 保存登录记录
     */
    fun saveLoginRecord(user: UserBean): Flow<XSuperResult<Boolean>> {
        return resultFlow {
            userDao.insert(user)
            true
        }
    }

    /**
     * 获取登录记录
     */
    suspend fun getLoginRecord(): Flow<XSuperResult<List<UserBean>>> {
        return resultFlow {
            userDao.getAll() ?: emptyList()
        }
    }

    /**
     * 注册账号
     * @param userName 用户名
     * @param password 密码
     */
    suspend fun register(userName: String, password: String) =
        userApis.register(userName, password, password).asApiFlow()
}
