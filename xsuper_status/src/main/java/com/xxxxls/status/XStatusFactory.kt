package com.xxxxls.status

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

/**
 * 多状态构建
 * @author Max
 * @date 2019-12-16.
 */
object XStatusFactory {

    /**
     * 构建多状态view
     */
    fun status(
        activity: Activity,
        @LayoutRes loadingRes: Int = View.NO_ID,
        @LayoutRes emptyRes: Int = View.NO_ID,
        @LayoutRes errorRes: Int = View.NO_ID,
        @LayoutRes noNetworkRes: Int = View.NO_ID,
        onRetry: (statusView: IStatusView, status: XStatus) -> Boolean = { _, _ -> true }
    ): XSuperStatusView? {
        return status(
            (activity.findViewById(android.R.id.content) as ViewGroup).getChildAt(0),
            loadingRes, emptyRes, errorRes, noNetworkRes, onRetry
        )
    }

    /**
     * 构建多状态view
     */
    fun status(
        fragment: Fragment,
        @LayoutRes loadingRes: Int = View.NO_ID,
        @LayoutRes emptyRes: Int = View.NO_ID,
        @LayoutRes errorRes: Int = View.NO_ID,
        @LayoutRes noNetworkRes: Int = View.NO_ID,
        onRetry: (statusView: IStatusView, status: XStatus) -> Boolean = { _, _ -> true }
    ): XSuperStatusView? {
        return status(fragment.view, loadingRes, emptyRes, errorRes, noNetworkRes, onRetry)
    }


    /**
     * 构建多状态view
     */
    fun status(
        contentView: View?,
        @LayoutRes loadingRes: Int = View.NO_ID,
        @LayoutRes emptyRes: Int = View.NO_ID,
        @LayoutRes errorRes: Int = View.NO_ID,
        @LayoutRes noNetworkRes: Int = View.NO_ID,
        onRetry: (statusView: IStatusView, status: XStatus) -> Boolean = { _, _ -> true }
    ): XSuperStatusView? {
        (contentView?.parent as? ViewGroup)?.apply {
            val layoutParams = contentView.layoutParams
            val index = this.indexOfChild(contentView)
            this.removeView(contentView)
            val statusView = XSuperStatusView(context)
            this.addView(statusView, index, layoutParams)
            statusView.addStatus(XStatus.Content, contentView)
            statusView.addStatus(XStatus.Error, errorRes)
            statusView.addStatus(XStatus.NoNetwork, noNetworkRes)
            statusView.addStatus(XStatus.Empty, emptyRes)
            statusView.addStatus(XStatus.Loading, loadingRes)
            statusView.switchStatus(XStatus.Content)
            statusView.setOnRetryClickListener(object : IStatusView.OnRetryClickListener {
                override fun onRetry(status: XStatus): Boolean {
                    return onRetry(statusView, status)
                }
            })
            return statusView
        }
        return null
    }
}