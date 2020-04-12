package com.xxxxls.adapter

import android.view.View
import androidx.annotation.IdRes
import androidx.annotation.Nullable
import androidx.recyclerview.widget.RecyclerView

/**
 * 基础adapter
 * @author Max
 * @date 2019-12-27.
 */
interface IAdapter<T> {

    /**
     * 获取RecycleView Adapter
     */
    fun getRecyclerViewAdapter(): RecyclerView.Adapter<*>

    /**
     * 获取所有列表数据
     */
    fun getData(): List<T>

    /**
     * 获取指定位置的数据
     */
    fun getItem(position: Int): T?

    /**
     * 获取条目视图
     * @param position 在列表中的位置
     * @return 对应视图
     */
    fun getItemView(position: Int): View?

    /**
     * 获取条目子view
     * @param position 在列表中的位置
     * @param viewId viewId
     *
     */
    fun getItemChildView(position: Int, @IdRes viewId: Int): View?

    /**
     * 添加条目
     * @param newData 新加的条目数据
     * @param position 为空时默认尾部追加
     */
    fun addData(newData: T, position: Int? = null)

    /**
     * 添加条目
     * @param newData 新加的条目数据列表
     * @param position 为空时默认尾部追加
     */
    fun addData(newData: List<T>, position: Int? = null)

    /**
     * 移除指定条目
     * @param position 在列表中的位置
     */
    fun remove(position: Int)

    /**
     * 移除所有条目
     */
    fun removeAll()

    /**
     * 替换条目
     * @param data 替换的数据
     */
    fun replaceData(data: List<T>)


    /**
     * 设置列表条目点击事件
     */
    fun setOnItemClickListener(listener: (adapter: IAdapter<out T>, view: View, position: Int) -> Unit)

    /**
     * 设置列表条目点击事件
     */
    fun setOnItemLongClickListener(listener: (adapter: IAdapter<out T>, view: View, position: Int) -> Boolean)

    /**
     * 设置列表条目子VIEW点击事件
     */
    fun setOnItemChildClickListener(listener: (adapter: IAdapter<out T>, view: View, position: Int) -> Unit)

    /**
     * 设置列表条目子VIEW长按点击事件
     */
    fun setOnItemChildLongClickListener(listener: (adapter: IAdapter<out T>, view: View, position: Int) -> Boolean)

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
}