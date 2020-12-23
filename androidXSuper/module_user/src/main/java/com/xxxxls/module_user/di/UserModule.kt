package com.xxxxls.module_user.di

import com.xxxxls.module_user.data.UserApis
import com.xxxxls.module_user.data.UserRepository
import com.xxxxls.module_user.db.UserDao
import com.xxxxls.module_user.db.UserDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * 用户模块
 * @author Max
 * @date 2020/11/27.
 */
@Module
@InstallIn(SingletonComponent::class)
class UserModule {

    /**
     * 提供 - UserDatabase
     */
    @Singleton
    @Provides
    fun provideUserDatabase(): UserDatabase {
        return UserDatabase.newInstance()
    }

    /**
     * 提供 - UserAPI
     */
    @Singleton
    @Provides
    fun provideUserApi(retrofit: Retrofit): UserApis {
        return retrofit.create(UserApis::class.java)
    }

    /**
     * 提供 - UserDao
     */
    @Singleton
    @Provides
    fun provideUserDao(userDatabase: UserDatabase): UserDao {
        return userDatabase.getUserDao()
    }
}