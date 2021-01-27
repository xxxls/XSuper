package com.xxxxls.status

import android.view.View
import androidx.annotation.LayoutRes

/**
 *
 * @author Max
 * @date 2019-12-16.
 */
interface IStatusView {

    /**
     * 切换状态
     * @param status 状态
     * @param delayMillis 延时切换（毫秒）
     */
    fun switchStatus(status: Status, delayMillis: Long = 0L)

    /**
     * 获取状态
     */
    fun getStatus(): Status

    /**
     * 添加状态视图
     * @param status 状态类型
     * @param statusView 状态视图
     */
    fun addStatus(status: Status, statusView: View)

    /**
     * 添加状态视图
     * @param status 状态类型
     * @param statusLayoutId 状态视图布局ID
     */
    fun addStatus(status: Status, @LayoutRes statusLayoutId: Int)

    /**
     * 移除状态
     * @return 是否移除成功
     */
    fun removeStatus(status: Status): Boolean

    /**
     * 设置状态更新时间
     */
    fun setOnStatusChangeListener(listener: OnStatusChangeListener?)

    /**
     * 设置重试点击事件
     */
    fun setOnRetryClickListener(listener: OnRetryClickListener?)

    /**
     * 获取某状态对应的视图
     * @param status 状态
     */
    fun getViewByStatus(status: Status): View?

    //状态改变状态
    interface OnStatusChangeListener {

        /**
         * 改变状态
         * @param newStatus 新状态
         * @param oldStatus 旧状态
         */
        fun onChange(newStatus: Status,oldStatus: Status)
    }

    //重试事件
    interface OnRetryClickListener {

        /**
         * 重试操作
         * @param statusView
         * @param status 触发时的状态
         */
        fun onRetry(statusView: IStatusView, status: Status)
    }
}