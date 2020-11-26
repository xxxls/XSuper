package com.xxxxls.module_base.module

import com.xxxxls.module_base.constants.NetConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * 网络模块
 * @author Max
 * @date 2020/11/25.
 */
@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    /**
     * 提供 - OkHttp
     */
    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder().build()
    }

    /**
     * 提供 - Retrofit
     */
    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(NetConfig.BASE_URL)
            .client(okHttpClient)
            .build()
    }

}