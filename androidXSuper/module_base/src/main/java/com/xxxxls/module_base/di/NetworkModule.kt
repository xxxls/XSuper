package com.xxxxls.module_base.di

import com.xxxxls.module_base.BuildConfig
import com.xxxxls.module_base.constants.NetConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * 网络模块
 * @author Max
 * @date 2020/11/25.
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val TIME_OUT_SECONDS = 15

    /**
     * 提供 - OkHttp
     */
    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder()
            .connectTimeout(
                TIME_OUT_SECONDS.toLong(),
                TimeUnit.SECONDS
            )
            .readTimeout(
                TIME_OUT_SECONDS.toLong(),
                TimeUnit.SECONDS
            )
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = when (BuildConfig.DEBUG) {
                    true -> HttpLoggingInterceptor.Level.BODY
                    false -> HttpLoggingInterceptor.Level.NONE
                }
            })
            .build()
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
//            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

//    /**
//     * 提供 - ResponseAdapter
//     */
//    @Singleton
//    @Provides
//    fun provideResponseAdapter(): ApiResponseAdapter {
//        return ApiResponseAdapter()
//    }

}