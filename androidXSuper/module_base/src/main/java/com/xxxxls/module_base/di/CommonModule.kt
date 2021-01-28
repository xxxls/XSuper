package com.xxxxls.module_base.di

import com.xxxxls.module_base.refresh.SmartListRefreshHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * 网络模块
 * @author Max
 * @date 2020/11/25.
 */
@Module
@InstallIn(SingletonComponent::class)
object CommonModule {


    /**
     * 提供 - 智能刷新配置器
     */
    @Singleton
    @Provides
    fun provideRefreshConfig(): SmartListRefreshHelper.Config {
        return SmartListRefreshHelper.Config(0, 3)
    }

}