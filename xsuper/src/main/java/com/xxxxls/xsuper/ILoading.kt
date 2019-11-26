package com.xxxxls.xsuper

/**
 * 进度视图
 * @author Max
 * @date 2019-11-26.
 */
interface ILoading {

    /**
     * 展示进度
     * @param id
     * @param message 消息
     */
    fun showLoading(id: Int = 0, message: CharSequence? = null)

    /**
     * 关闭进度
     * @param id
     */
    fun dismissLoading(id: Int = 0)
}