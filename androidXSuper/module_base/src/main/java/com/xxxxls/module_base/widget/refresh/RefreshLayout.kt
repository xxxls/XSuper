package com.xxxxls.module_base.widget.refresh

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView
import com.scwang.smart.refresh.layout.SmartRefreshLayout
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener

/**
 * 列表刷新器（SmartRefreshLayout）
 * @author Max
 * @date 1/28/21.
 */
class RefreshLayout : SmartRefreshLayout, IRefreshLayout, OnRefreshLoadMoreListener {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    private var onRefresh: ((IRefreshLayout) -> Unit)? = null
    private var onLoadMore: ((IRefreshLayout) -> Unit)? = null


    override fun autoRefresh(delayed: Int?): Boolean {
        return super.autoRefresh(delayed ?: 0)
    }

    override fun autoLoadMore(delayed: Int?): Boolean {
        return super.autoLoadMore(delayed ?: 0)
    }

    override fun getRecyclerView(): RecyclerView? {
        return layout.getChildAt(0) as? RecyclerView
    }

    override fun finishRefresh(success: Boolean, noMoreData: Boolean, delayed: Int?) {
        super.finishRefresh(delayed ?: 0, success, noMoreData)
    }

    override fun finishLoadMore(success: Boolean, noMoreData: Boolean, delayed: Int?) {
        super.finishLoadMore(delayed ?: 0, success, noMoreData)
    }

    override fun setNoMoreData(noMoreData: Boolean, tag: Any?) {
        super.setNoMoreData(noMoreData)
    }

    override fun setEnableRefresh(enabled: Boolean, tag: Any?) {
        super.setEnableRefresh(enabled)
    }

    override fun setEnableLoadMore(enabled: Boolean, tag: Any?) {
        super.setEnableLoadMore(enabled)
    }

    override fun setOnRefreshLoadMoreListener(
        onRefresh: (IRefreshLayout) -> Unit,
        onLoadMore: (IRefreshLayout) -> Unit
    ) {
        this.onRefresh = onRefresh
        this.onLoadMore = onLoadMore
        super.setOnRefreshLoadMoreListener(this)
    }

    override fun onRefresh(refreshLayout: RefreshLayout) {
        onRefresh?.invoke(this)
    }

    override fun onLoadMore(refreshLayout: RefreshLayout) {
        onLoadMore?.invoke(this)
    }
}