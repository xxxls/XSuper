package com.xxxxls.status

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

/**
 * 多状态构建
 * @author Max
 * @date 2019-12-16.
 */
object StatusFactory {

    /**
     * 构建多状态view
     */
    fun status(
        activity: Activity,
        @LayoutRes loadingRes: Int = View.NO_ID,
        @LayoutRes emptyRes: Int = View.NO_ID,
        @LayoutRes errorRes: Int = View.NO_ID,
        @LayoutRes noNetworkRes: Int = View.NO_ID,
        //重试时是否自动切换loading状态
        isAutoSwitchLoading: Boolean = true,
        onRetry: ((status: Status) -> Unit)? = null
    ): SuperStatusView? {
        return status(
            (activity.findViewById(android.R.id.content) as ViewGroup).getChildAt(0),
            loadingRes, emptyRes, errorRes, noNetworkRes, isAutoSwitchLoading, onRetry
        )
    }

    /**
     * 构建多状态view
     */
    fun status(
        activity: Activity,
        @IdRes contentViewResId: Int,
        @LayoutRes loadingRes: Int = View.NO_ID,
        @LayoutRes emptyRes: Int = View.NO_ID,
        @LayoutRes errorRes: Int = View.NO_ID,
        @LayoutRes noNetworkRes: Int = View.NO_ID,
        //重试时是否自动切换loading状态
        isAutoSwitchLoading: Boolean = true,
        onRetry: ((status: Status) -> Unit)? = null
    ): SuperStatusView? {
        (activity.findViewById(android.R.id.content) as ViewGroup).findViewById<View>(
            contentViewResId
        )?.apply {
            return status(
                this, loadingRes, emptyRes, errorRes, noNetworkRes, isAutoSwitchLoading, onRetry
            )
        }
        return null
    }

    /**
     * 构建多状态view
     */
    fun status(
        fragment: Fragment,
        @IdRes contentViewResId: Int,
        @LayoutRes loadingRes: Int = View.NO_ID,
        @LayoutRes emptyRes: Int = View.NO_ID,
        @LayoutRes errorRes: Int = View.NO_ID,
        @LayoutRes noNetworkRes: Int = View.NO_ID,
        //重试时是否自动切换loading状态
        isAutoSwitchLoading: Boolean = true,
        onRetry: ((status: Status) -> Unit)? = null
    ): SuperStatusView? {
        fragment.view?.findViewById<View>(contentViewResId)?.apply {
            return status(
                this,
                loadingRes,
                emptyRes,
                errorRes,
                noNetworkRes,
                isAutoSwitchLoading,
                onRetry
            )
        }
        return null
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
        //重试时是否自动切换loading状态
        isAutoSwitchLoading: Boolean = true,
        onRetry: ((status: Status) -> Unit)? = null
    ): SuperStatusView? {
        return status(
            contentView,
            loadingRes,
            emptyRes,
            errorRes,
            noNetworkRes,
            object : IStatusView.OnRetryClickListener {
                override fun onRetry(statusView: IStatusView, status: Status) {
                    if (isAutoSwitchLoading) {
                        statusView.switchStatus(Status.Loading)
                    }
                    onRetry?.invoke(status)
                }
            })
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
        onRetry: IStatusView.OnRetryClickListener? = null
    ): SuperStatusView? {
        (contentView?.parent as? ViewGroup)?.apply {
            val layoutParams = contentView.layoutParams
            val index = this.indexOfChild(contentView)
            this.removeView(contentView)
            val statusView = SuperStatusView(context)
            this.addView(statusView, index, layoutParams)
            statusView.addStatus(Status.Content, contentView)
            statusView.addStatus(Status.Error, errorRes)
            statusView.addStatus(Status.NoNetwork, noNetworkRes)
            statusView.addStatus(Status.Empty, emptyRes)
            statusView.addStatus(Status.Loading, loadingRes)
            statusView.switchStatus(Status.Content)
            statusView.setOnRetryClickListener(onRetry)
            return statusView
        }
        return null
    }
}