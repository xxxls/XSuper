package com.xxxxls.module_base.response

import com.google.gson.annotations.SerializedName
import com.xxxxls.xsuper.model.XSuperResponse

/**
 * 基础响应体
 * @author Max
 * @date 2019-11-28.
 */
open class BaseResponse<out T> : XSuperResponse<T> {

    //响应体
    val data: T? = null

    //错误码
    @SerializedName("errorCode")
    var code: Int? = null

    //错误信息
    @SerializedName("errorMsg")
    var message: String? = null

    override fun getBody(): T? {
        return data
    }

    override fun isSuccess(): Boolean {
        return code == 0
    }

}