package com.xxxxls.example.ui.paging.data

import com.xxxxls.example.ui.paging.data.api.PagingApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import retrofit2.Retrofit

/**
 * 提供模块
 * @author Max
 * @date 2020/11/26.
 */
@Module
@InstallIn(ActivityComponent::class)
class PagingModule {

    /**
     * 提供 - API
     */
    @Provides
    fun provideApi(retrofit: Retrofit): PagingApi {
        return retrofit.create(PagingApi::class.java)
    }

}