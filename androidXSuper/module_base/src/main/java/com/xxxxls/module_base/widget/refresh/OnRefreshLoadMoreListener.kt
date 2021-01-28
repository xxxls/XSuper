package com.xxxxls.module_base.widget.refresh

/**
 * 刷新加载更多事件
 * @author Max
 * @date 1/28/21.
 */
interface OnRefreshLoadMoreListener {

    /**
     * 刷新
     */
    fun onRefresh()

    /**
     * 加载更多
     */
    fun onLoadMore()
}