package com.xxxxls.module_base.adapter

import android.view.View
import androidx.annotation.Nullable
import androidx.recyclerview.widget.RecyclerView

/**
 * 列表适配器
 * @author Max
 * @date 1/28/21.
 */
interface IListAdapter<T> {
    /**
     * 获取RecycleView Adapter
     */
    fun getRecyclerViewAdapter(): RecyclerView.Adapter<*>

    /**
     * 添加数据
     */
    fun addData(list: List<T>)

    /**
     * 添加数据
     * @param position 指定索引
     */
    fun addData(position: Int, item: T)

    /**
     * 移除指定位置条目
     */
    fun remove(position: Int)

    /**
     * 获取数据源
     */
    fun getDatas(): List<T>

    /**
     * 获取指定条目
     */
    fun getItem(position: Int): T?

    /**
     * 替换数据
     */
    fun replaceData(list: List<T>)

    /**
     * 设置列表条目点击事件
     */
    fun setOnItemClickListener(listener: (adapter: IListAdapter<out T>, view: View, position: Int) -> Unit)

    /**
     * 设置列表条目点击事件
     */
    fun setOnItemLongClickListener(listener: (adapter: IListAdapter<out T>, view: View, position: Int) -> Boolean)

    /**
     * 设置列表条目子VIEW点击事件
     */
    fun setOnItemChildClickListener(listener: (adapter: IListAdapter<out T>, view: View, position: Int) -> Unit)

    /**
     * 设置列表条目子VIEW长按点击事件
     */
    fun setOnItemChildLongClickListener(listener: (adapter: IListAdapter<out T>, view: View, position: Int) -> Boolean)

    /**
     * notifyDataSetChanged
     */
    fun notifyDataSetChanged()

    /**
     * notifyItemRangeChanged
     */
    fun notifyItemRangeChanged(positionStart: Int, itemCount: Int, @Nullable payload: Any? = null)

    /**
     * notifyItemMoved
     */
    fun notifyItemMoved(fromPosition: Int, toPosition: Int)

    /**
     * notifyItemRangeInserted
     */
    fun notifyItemRangeInserted(positionStart: Int, itemCount: Int)

    /**
     * notifyItemRemoved
     */
    fun notifyItemRemoved(position: Int)

    /**
     * notifyItemRangeRemoved
     */
    fun notifyItemRangeRemoved(positionStart: Int, itemCount: Int)

    /**
     * notifyItemInserted
     */
    fun notifyItemInserted(position: Int)

    /**
     * notifyItemChanged
     */
    fun notifyItemChanged(position: Int, @Nullable payload: Any? = null)

    /**
     * getItemCount
     */
    fun getItemCount(): Int

    /**
     * getItemViewType
     */
    fun getItemViewType(position: Int): Int

    /**
     * 编辑单个数据
     * @param position 索引
     * @param payload
     * @param call 自定义操作
     */
    fun edit(position: Int, payload: Any? = null, call: ((item: T) -> Unit)? = null) {
        getDatas().getOrNull(position)?.let {
            call?.invoke(it)
            notifyItemChanged(position, payload)
        }
    }

    /**
     * 编辑符合条件的数据
     * @param predicate 检测符合条件的项
     * @param payload
     * @param call 自定义操作
     */
    fun edit(
        predicate: (position: Int, item: T) -> Boolean,
        payload: Any? = null,
        call: ((position: Int, item: T) -> Unit)? = null
    ) {
        getDatas().forEachIndexed { index, t ->
            if (predicate(index, t)) {
                call?.invoke(index, t)
                notifyItemChanged(index, payload)
            }
        }
    }

    /**
     * 设置预加载
     * @param preLoadNumber 滑动到倒数第几个时,就开始加载下一页数据。(当该数 大于0 时才有效)
     * @param listener 预加载回调
     */
    fun setPreLoadNumber(preLoadNumber: Int?, listener: () -> Unit)

}