package com.xxxxls.xsuper.net.call

import androidx.annotation.NonNull
import androidx.lifecycle.Observer
import com.xxxxls.xsuper.net.XSuperResult

/**
 * super - Call
 * @author Max
 * @date 2019-11-28.
 */
interface XSuperCall<T> {

    /**
     * 执行异步请求，但不需要绑定组件生命周期(获取全部状态结果)
     * @param callback 请求回调
     */
    fun enqueue(@NonNull callback: XSuperCall<XSuperResult<T>>)

    /**
     * 发起同步网络请求
     * @return 请求结果
     */
    fun execute(): XSuperResult<T>

    /**
     * 取消请求
     */
    abstract fun cancel()

    /**
     * 是否被取消
     *
     * @return 是否取消
     */
    abstract fun isCancelled(): Boolean
}