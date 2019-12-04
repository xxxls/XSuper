package com.xxxxls.module_user.net

import com.xxxxls.module_base.net.response.BaseResponse
import com.xxxxls.module_user.bean.UserBean
import kotlinx.coroutines.Deferred
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * 用户模块 - API接口
 * @author Max
 * @date 2019-11-28.
 */
interface UserApis {

    /**
     * 用户登录
     * @param userName 用户名
     * @param passWord 用户密码
     */
    @FormUrlEncoded
    @POST("/user/login")
    fun login(
        @Field("username") userName: String,
        @Field("password") passWord: String
    ): Deferred<BaseResponse<UserBean>>


    /**
     * 用户注册
     * @param userName 用户名
     * @param passWord 用户密码
     * @param rePassWord 确认密码
     */
    @FormUrlEncoded
    @POST("/user/register")
    fun register(
        @Field("username") userName: String,
        @Field("password") passWord: String,
        @Field("repassword") rePassWord: String
    ): Deferred<BaseResponse<UserBean>>


}