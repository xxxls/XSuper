package com.xxxxls.module_base.net.response

import com.xxxxls.xsuper.net.XSuperResponse

/**
 * 基础响应体
 * @author Max
 * @date 2019-11-28.
 */
data class BaseResponse<out T>(
    //响应体
    val data: T? = null,

    //错误码
    var errorCode: Int?,

    //错误信息
    var errorMsg: String
) : XSuperResponse<T> {

    override fun getBody(): T? {
        return data
    }

    override fun isSuccess(): Boolean {
        return errorCode == 0
    }

}