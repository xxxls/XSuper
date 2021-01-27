package com.xxxxls.module_base.network.response

import com.google.gson.annotations.SerializedName
import com.xxxxls.xsuper.model.SuperResponse

/**
 * 基础响应体
 * @author Max
 * @date 2019-11-28.
 */
open class BaseResponse<out T> : SuperResponse<T> {

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

    /**
     * 成功的处理
     */
    fun doSuccess(block: (T?) -> Unit) {
        if (isSuccess()) {
            block.invoke(getBody())
        }
    }

    /**
     * 成功且结果不为空的处理
     */
    suspend fun doSuccessNotEmpty(block: suspend (T) -> Unit) {
        if (isSuccess()) {
            getBody()?.let {
                block.invoke(it)
            }
        }
    }

    /**
     * 失败的处理
     */
    fun doFailure(block: (BaseResponse<T>) -> Unit) {
        if (!isSuccess()) {
            block.invoke(this)
        }
    }
}