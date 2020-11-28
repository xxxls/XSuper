package com.xxxxls.module_base.di

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.xxxxls.module_base.constants.NetConfig
import com.xxxxls.module_base.interceptors.LoggerInterceptor
import com.xxxxls.module_base.network.ApiResponseAdapter
import com.xxxxls.xsuper.adapter.ResponseAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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
@InstallIn(SingletonComponent::class)
object NetworkModule {

    /**
     * 提供 - OkHttp
     */
    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder().addInterceptor(LoggerInterceptor()).build()
    }

    /**
     * 提供 - Retrofit
     */
    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(NetConfig.BASE_URL)
            .client(okHttpClient)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    /**
     * 提供 - ResponseAdapter
     */
    @Singleton
    @Provides
    fun provideResponseAdapter(): ResponseAdapter {
        return ApiResponseAdapter()
    }

}