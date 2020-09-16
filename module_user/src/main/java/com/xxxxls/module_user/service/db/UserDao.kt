package com.xxxxls.module_user.service.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
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
    @Query("SELECT * FROM tb_user")
    fun getAll(): List<UserBean>?

    /**
     * 插入单条
     */
    @Insert
    fun insert(user: UserBean)

    /**
     * 删除所有
     */
    @Query("DELETE FROM tb_user")
    fun deleteAll()
}