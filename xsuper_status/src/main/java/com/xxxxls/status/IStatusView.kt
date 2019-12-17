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
     */
    fun switchStatus(status: XStatus)

    /**
     * 获取状态
     */
    fun getStatus(): XStatus

    /**
     * 添加状态视图
     * @param status 状态类型
     * @param statusView 状态视图
     */
    fun addStatus(status: XStatus, statusView: View)

    /**
     * 添加状态视图
     * @param status 状态类型
     * @param statusLayoutId 状态视图布局ID
     */
    fun addStatus(status: XStatus, @LayoutRes statusLayoutId: Int)

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
    fun getViewByStatus(status: XStatus): View?

    //状态改变状态
    interface OnStatusChangeListener {

        /**
         * 改变状态
         * @param oldStatus 旧状态
         * @param newStatus 新状态
         */
        fun onChange(oldStatus: XStatus, newStatus: XStatus)
    }

    //重试事件
    interface OnRetryClickListener {

        /**
         * 重试操作
         * @param status 触发时的状态
         * @return 是否切换加载状态
         */
        fun onRetry(status: XStatus): Boolean
    }
}