package com.xxxxls.status

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import org.w3c.dom.Text

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
    //重试回调，返回true时自动切换加载状态
    onRetry: (status: XStatus) -> Boolean = { _ -> true }
): XSuperStatusView? {
    return XStatusFactory.status(this, loadingRes, emptyRes, errorRes, noNetworkRes, onRetry)
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
): XSuperStatusView? {
    return XStatusFactory.status(this, loadingRes, emptyRes, errorRes, noNetworkRes, onRetry)
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
fun View.getStatusView(status: XStatus): View? {
    return getStatusView()?.getViewByStatus(status)
}

/**
 * 获取某个状态视图 的子view
 */
fun <V : View> View.getStatusChildView(status: XStatus, @IdRes viewId: Int): V? {
    return getStatusView(status)?.findViewById<V>(viewId)
}

/**
 * 切换状态
 */
fun View.switchStatus(status: XStatus) {
    getStatusView()?.run {
        this.switchStatus(status)
    }
}


/**
 * 切换状态 - Content
 */
fun View.showContent() {
    getStatusView()?.run {
        this.switchStatus(XStatus.Content)
    }
}


/**
 * 切换状态 - Loading
 */
fun View.showLoading() {
    getStatusView()?.run {
        this.switchStatus(XStatus.Loading)
    }
}


/**
 * 切换状态 - Error
 */
fun View.showError() {
    getStatusView()?.run {
        this.switchStatus(XStatus.Error)
    }
}


/**
 * 切换状态 - Empty
 */
fun View.showEmpty() {
    getStatusView()?.run {
        this.switchStatus(XStatus.Empty)
    }
}


/**
 * 切换状态 - NoNetwork
 */
fun View.showNoNetwork() {
    getStatusView()?.run {
        this.switchStatus(XStatus.NoNetwork)
    }
}


/**
 * 设置状态视图 某view的文本
 * @param status 状态
 * @param viewId
 * @param text 文本
 */
fun View.setStatusText(status: XStatus, viewId: Int, text: CharSequence) {
    getStatusChildView<TextView>(status, viewId)?.text = text
}

/**
 * 设置状态视图 某view的文本
 * @param status 状态
 * @param viewId
 * @param resId 文本资源
 */
fun View.setStatusText(status: XStatus, viewId: Int, @StringRes resId: Int) {
    getStatusChildView<TextView>(status, viewId)?.setText(resId)
}


/**
 * 设置状态视图 某view的图片
 * @param status 状态
 * @param viewId
 * @param resId 资源ID
 */
fun View.setStatusImage(status: XStatus, viewId: Int, @DrawableRes resId: Int) {
    getStatusChildView<ImageView>(status, viewId)?.setImageResource(resId)
}

/**
 * 设置状态视图 某view的点击事件
 * @param status 状态
 * @param viewId
 * @param listener 点击事件
 */
fun View.setOnStatusChildViewClickListener(
    status: XStatus,
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
    status: XStatus,
    viewId: Int,
    listener: View.OnClickListener
) {
    getStatusChildView<View>(status, viewId)?.setOnClickListener(listener)
}

/**
 * 设置重试监听
 */
fun View.setOnStatusRetryListener(
    onRetry: (status: XStatus) -> Boolean
) {
    this.setOnStatusRetryListener(object : IStatusView.OnRetryClickListener {
        override fun onRetry(status: XStatus): Boolean {
            return onRetry(status)
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