package com.xxxxls.module_user.db

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.xxxxls.module_user.bean.UserBean
import com.xxxxls.utils.AppUtils

/**
 * 用户数据库
 * @author Max
 * @date 2020/9/10.
 */
@Database(entities = [UserBean::class], version = 1)
abstract class UserDatabase : RoomDatabase() {

    companion object {
        private const val DB_NAME = "user_database.db"

        fun newInstance(): UserDatabase {
            return Room.databaseBuilder(AppUtils.getApp(), UserDatabase::class.java, DB_NAME)
                .build()
        }
    }

    abstract fun getUserDao(): UserDao
}