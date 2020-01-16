package com.xxxxls.adapter

import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.NonNull
import com.xxxxls.adapter.listener.OnItemClickListener
import com.xxxxls.adapter.listener.OnItemLongClickListener

/**
 * SuperAdapter 公共基础处理
 * @author Max
 * @date 2020-01-14.
 */
internal interface XSuperAdapterEngine<T, VH : XSuperViewHolder> {

    fun getAdapter(): IXSuperAdapter<T>

    /**
     * Adapter - onCreateViewHolder
     */
    fun xCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return createSuperViewHolder(getItemView(getItemViewLayoutId(viewType), parent)).apply {
            adapter = getAdapter()
            bindItemViewListener(this)
        }
    }

    /**
     * Adapter - onBindViewHolder
     */
    fun xBindViewHolder(holder: VH, position: Int) {
        convert(holder, getItem(position))
    }

    /**
     * Adapter - onBindViewHolder
     */
    fun xBindViewHolder(holder: VH, position: Int, payloads: MutableList<Any>) {
        if (payloads.isNullOrEmpty()) {
            xBindViewHolder(holder, position)
            return
        }
        convertPayloads(holder, getItem(position), payloads)
    }

    /**
     * 更新条目
     * @param helper
     * @param item
     */
    fun convert(@NonNull helper: VH, item: T?)

    /**
     * 局部更新条目
     * @param helper
     * @param item
     * @param payloads
     */
    fun convertPayloads(
        @NonNull helper: VH, item: T?,
        payloads: MutableList<Any>
    ) {

    }

    /**
     * 获取条目
     * @param position
     */
    fun getItem(position: Int): T?

    /**
     * 获取条目布局ID
     * @param itemType 条目类型
     */
    fun getItemViewLayoutId(itemType: Int): Int

    /**
     * 获取条目视图
     * @param layoutResId 布局ID
     * @param parent
     */
    fun getItemView(@LayoutRes layoutResId: Int, parent: ViewGroup): View

    /**
     * 创建VH
     * @param view 视图
     */
    fun createSuperViewHolder(view: View): VH

    /**
     * 绑定itemView事件
     * @param holder
     */
    fun bindItemViewListener(holder: VH) {
        //点击事件
        getOnItemClickListener()?.apply {
            holder.itemView.setOnClickListener {
                val position = holder.adapterPosition
                onItemClick(it, position)
            }
        }

        //长按事件
        getOnItemLongClickListener()?.apply {
            holder.itemView.setOnLongClickListener {
                val position = holder.adapterPosition
                onItemLongClick(it, position)
            }
        }
    }

    /**
     * 条目点击事件
     */
    fun getOnItemClickListener(): OnItemClickListener?

    /**
     * 条目长按点击事件
     */
    fun getOnItemLongClickListener(): OnItemLongClickListener?

}