package com.xxxxls.example.ui.network.data

import com.xxxxls.example.ui.network.data.api.ExampleApi
import com.xxxxls.example.ui.network.data.local.ExampleDao
import com.xxxxls.example.ui.network.data.local.ExampleDaoImpl
import com.xxxxls.example.ui.network.data.repository.ExampleRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import retrofit2.Retrofit

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
    @ActivityScoped
    @Provides
    fun provideApi(retrofit: Retrofit): ExampleApi {
        return retrofit.create(ExampleApi::class.java)
    }

    /**
     * 提供 - Dao
     */
    @ActivityScoped
    @Provides
    fun provideDao(): ExampleDao {
        return ExampleDaoImpl()
    }
}