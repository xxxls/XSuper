package com.xxxxls.example.ui.network.data

import com.xxxxls.example.ui.network.data.api.ExampleApi
import com.xxxxls.example.ui.network.data.local.ExampleDao
import com.xxxxls.example.ui.network.data.local.ExampleDaoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * 提供模块
 * @author Max
 * @date 2020/11/26.
 */
@Module
@InstallIn(ActivityComponent::class)
class ExampleModule {

    /**
     * 提供 - API
     */
    @Provides
    fun provideApi(retrofit: Retrofit): ExampleApi {
        return retrofit.create(ExampleApi::class.java)
    }

    /**
     * 提供 - Dao
     */
    @Provides
    fun provideDao(): ExampleDao {
        return ExampleDaoImpl()
    }
}