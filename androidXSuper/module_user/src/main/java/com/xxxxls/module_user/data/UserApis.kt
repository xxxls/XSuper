package com.xxxxls.module_user.data

import com.xxxxls.module_base.network.response.BaseResponse
import com.xxxxls.module_user.bean.LoginBean
import com.xxxxls.module_user.bean.UserBean
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
    suspend fun login(
        @Field("username") userName: String,
        @Field("password") passWord: String
    ): BaseResponse<LoginBean>

    /**
     * 用户注册
     * @param userName 用户名
     * @param passWord 用户密码
     * @param rePassWord 确认密码
     */
    @FormUrlEncoded
    @POST("/user/register")
    suspend fun register(
        @Field("username") userName: String,
        @Field("password") passWord: String,
        @Field("repassword") rePassWord: String
    ): BaseResponse<UserBean>

    /**
     * 获取用户信息 (这里模拟调用登录接口)
     * @param userName 用户名
     * @param passWord 用户密码
     * TODO 有的公司是先登录，登录会返回token，然后通过token再请求用户信息，
     * TODO 由于这里采用的WanAndroid提供的api，它不是这样的，所有这里继续调用登录接口来做示例。
     */
    @FormUrlEncoded
    @POST("/user/login")
    suspend fun getUserInfo(
        @Field("username") userName: String,
        @Field("password") passWord: String
    ): BaseResponse<UserBean>

}