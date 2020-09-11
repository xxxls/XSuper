package com.xxxxls.module_user.service.db

import androidx.room.Dao
import androidx.room.Query
import com.xxxxls.module_user.bean.UserBean


/**
 * 用户数据库
 * @author Max
 * @date 2020/9/10.
 */
@Dao
interface UserDao {

    /**
     * 获取所有
     */
    @Query("SELECT * FROM UserBean")
    fun getAll(): List<UserBean>?
}