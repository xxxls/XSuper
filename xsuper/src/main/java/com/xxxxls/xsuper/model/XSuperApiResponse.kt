package com.xxxxls.xsuper.model

/**
 * Super - Api-Response
 * @author Max
 * @date 2019-11-28.
 */
interface XSuperApiResponse<out T> {

    /**
     * 是否成功
     */
    fun isSuccess(): Boolean

}