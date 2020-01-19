package com.xxxxls.logger

/**
 * 日志参数
 * @author Max
 * @date 2020-01-19.
 */
interface LogParams {

    /**
     * 是否携带线程信息
     */
    fun withThread(): Boolean?

    /**
     * 是否展示边框
     */
    fun withBorder(): Boolean?

    /**
     * 堆栈跟踪信息深度
     */
    fun getStackTraceDepth(): Int?
}