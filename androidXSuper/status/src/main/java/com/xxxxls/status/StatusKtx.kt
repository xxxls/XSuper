package com.xxxxls.status

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes

/**
 * 多状态
 * @author Max
 * @date 2019-12-16.
 */


/**
 * 配置多状态视图
 */
fun View.statusConfig(
    @LayoutRes loadingRes: Int = View.NO_ID,
    @LayoutRes emptyRes: Int = View.NO_ID,
    @LayoutRes errorRes: Int = View.NO_ID,
    @LayoutRes noNetworkRes: Int = View.NO_ID,
    //重试时是否自动切换loading状态
    isAutoSwitchLoading: Boolean = true,
    //重试回调，返回true时自动切换加载状态
    onRetry: (status: Status) -> Unit = { }
): SuperStatusView? {
    return StatusFactory.status(
        this,
        loadingRes,
        emptyRes,
        errorRes,
        noNetworkRes,
        isAutoSwitchLoading,
        onRetry
    )
}

/**
 * 配置多状态视图
 */
fun View.statusConfig(
    @LayoutRes loadingRes: Int = View.NO_ID,
    @LayoutRes emptyRes: Int = View.NO_ID,
    @LayoutRes errorRes: Int = View.NO_ID,
    @LayoutRes noNetworkRes: Int = View.NO_ID,
    //重试回调，返回true时自动切换加载状态
    onRetry: IStatusView.OnRetryClickListener? = null
): SuperStatusView? {
    return StatusFactory.status(this, loadingRes, emptyRes, errorRes, noNetworkRes, onRetry)
}

/**
 * 获取状态视图
 */
fun View.getStatusView(): IStatusView? {
    return (this.parent as? IStatusView)
}

/**
 * 获取某个状态视图View
 * @param status 状态
 */
fun View.getStatusView(status: Status): View? {
    return getStatusView()?.getViewByStatus(status)
}

/**
 * 获取某个状态视图 的子view
 */
fun <V : View> View.getStatusChildView(status: Status, @IdRes viewId: Int): V? {
    return getStatusView(status)?.findViewById<V>(viewId)
}

/**
 * 切换状态
 * @param delayMillis 延迟时长（毫秒）
 */
fun View.switchStatus(status: Status, delayMillis: Long = 0L) {
    getStatusView()?.run {
        this.switchStatus(status, delayMillis)
    }
}


/**
 * 切换状态 - Content
 */
fun View.showContent(delayMillis: Long = 0L) {
    getStatusView()?.run {
        this.switchStatus(Status.Content, delayMillis)
    }
}

fun IStatusView?.showContent(delayMillis: Long = 0L) {
    this?.switchStatus(Status.Content, delayMillis)
}

/**
 * 切换状态 - Loading
 */
fun View.showLoading(delayMillis: Long = 0L) {
    getStatusView()?.run {
        this.switchStatus(Status.Loading, delayMillis)
    }
}

fun IStatusView?.showLoading(delayMillis: Long = 0L) {
    this?.switchStatus(Status.Loading, delayMillis)
}

/**
 * 切换状态 - Error
 */
fun View.showError(delayMillis: Long = 0L) {
    getStatusView()?.run {
        this.switchStatus(Status.Error, delayMillis)
    }
}

fun IStatusView?.showError(delayMillis: Long = 0L) {
    this?.switchStatus(Status.Error, delayMillis)
}

/**
 * 切换状态 - Empty
 */
fun View.showEmpty(delayMillis: Long = 0L) {
    getStatusView()?.run {
        this.switchStatus(Status.Empty, delayMillis)
    }
}

fun IStatusView?.showEmpty(delayMillis: Long = 0L) {
    this?.switchStatus(Status.Empty, delayMillis)
}

/**
 * 切换状态 - NoNetwork
 */
fun View.showNoNetwork(delayMillis: Long = 0L) {
    getStatusView()?.run {
        this.switchStatus(Status.NoNetwork, delayMillis)
    }
}

fun IStatusView?.showNoNetwork(delayMillis: Long = 0L) {
    this?.switchStatus(Status.NoNetwork, delayMillis)
}


/**
 * 设置状态视图 某view的文本
 * @param status 状态
 * @param viewId
 * @param text 文本
 */
fun View.setStatusText(status: Status, viewId: Int, text: CharSequence) {
    getStatusChildView<TextView>(status, viewId)?.text = text
}

/**
 * 设置状态视图 某view的文本
 * @param status 状态
 * @param viewId
 * @param resId 文本资源
 */
fun View.setStatusText(status: Status, viewId: Int, @StringRes resId: Int) {
    getStatusChildView<TextView>(status, viewId)?.setText(resId)
}


/**
 * 设置状态视图 某view的图片
 * @param status 状态
 * @param viewId
 * @param resId 资源ID
 */
fun View.setStatusImage(status: Status, viewId: Int, @DrawableRes resId: Int) {
    getStatusChildView<ImageView>(status, viewId)?.setImageResource(resId)
}

/**
 * 设置状态视图 某view的点击事件
 * @param status 状态
 * @param viewId
 * @param listener 点击事件
 */
fun View.setOnStatusChildViewClickListener(
    status: Status,
    viewId: Int,
    listener: (v: View?) -> Unit
) {
    setOnStatusChildViewClickListener(status, viewId, View.OnClickListener { v -> listener(v) })
}


/**
 * 设置状态视图 某view的点击事件
 * @param status 状态
 * @param viewId
 * @param listener 点击事件
 */
fun View.setOnStatusChildViewClickListener(
    status: Status,
    viewId: Int,
    listener: View.OnClickListener
) {
    getStatusChildView<View>(status, viewId)?.setOnClickListener(listener)
}

/**
 * 设置重试监听
 */
fun View.setOnStatusRetryListener(
    //重试时是否自动切换loading状态
    isAutoSwitchLoading: Boolean = true,
    onRetry: (status: Status) -> Boolean
) {
    this.setOnStatusRetryListener(object : IStatusView.OnRetryClickListener {
        override fun onRetry(statusView: IStatusView, status: Status) {
            if (isAutoSwitchLoading) {
                statusView.switchStatus(Status.Loading)
            }
            onRetry(status)
        }
    })
}

/**
 * 设置重试监听
 */
fun View.setOnStatusRetryListener(
    onRetry: IStatusView.OnRetryClickListener
) {
    getStatusView()?.run {
        this.setOnRetryClickListener(onRetry)
    }
}
