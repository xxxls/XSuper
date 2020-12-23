package com.xxxxls.example.ui.paging.data

import androidx.paging.PagingConfig
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

    /**
     * 提供 - PagingConfig
     */
    @Provides
    fun providePagingConfig(): PagingConfig {
        return PagingConfig(
            // 每页显示的数据的大小
            pageSize = 20,

            // 开启占位符
            enablePlaceholders = false

            // 预刷新的距离，距离最后一个 item 多远时加载数据
//        prefetchDistance = 3,

            /**
             * 初始化加载数量，默认为 pageSize * 3
             *
             * internal const val DEFAULT_INITIAL_PAGE_MULTIPLIER = 3
             * val initialLoadSize: Int = pageSize * DEFAULT_INITIAL_PAGE_MULTIPLIER
             */
//        initialLoadSize = 60
        )
    }

}