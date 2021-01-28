package com.xxxxls.module_base.adapter

import android.view.View
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.viewholder.BaseViewHolder

/**
 * 基础列表适配器（BaseQuickAdapter）
 * @author Max
 * @date 1/28/21.
 */
abstract class BaseListAdapter<T, H : BaseViewHolder>(
    @LayoutRes private val layoutResId: Int,
    data: MutableList<T>? = ArrayList()
) : BaseQuickAdapter<T, H>(layoutResId, data), IListAdapter<T> {


    private var onItemClickListener:
            ((adapter: IListAdapter<out T>, v: View, position: Int) -> Unit)? = null
    private var onItemLongClickListener:
            ((adapter: IListAdapter<out T>, v: View, position: Int) -> Boolean)? = null
    private var onItemChildClickListener:
            ((adapter: IListAdapter<out T>, v: View, position: Int) -> Unit)? = null
    private var onItemChildLongClickListener:
            ((adapter: IListAdapter<out T>, v: View, position: Int) -> Boolean)? = null

    override fun getRecyclerViewAdapter(): RecyclerView.Adapter<*> {
        return this
    }

    override fun addData(list: List<T>) {
        super.addData(list)
    }

    override fun addData(position: Int, data: T) {
        super<BaseQuickAdapter>.addData(position, data)
    }

    override fun getDatas(): List<T> {
        return super.data
    }

    override fun replaceData(list: List<T>) {
        super.setList(list)
    }

    override fun setOnItemClickListener(listener: (adapter: IListAdapter<out T>, view: View, position: Int) -> Unit) {
        onItemClickListener = listener
        super.setOnItemClickListener { adapter, view, position ->
            onItemClickListener?.invoke(
                this,
                view,
                position
            )
        }
    }

    override fun setOnItemLongClickListener(listener: (adapter: IListAdapter<out T>, view: View, position: Int) -> Boolean) {
        onItemLongClickListener = listener
        super.setOnItemLongClickListener { adapter, view, position ->
            onItemLongClickListener?.invoke(this, view, position) ?: false
        }
    }

    override fun setOnItemChildClickListener(listener: (adapter: IListAdapter<out T>, view: View, position: Int) -> Unit) {
        onItemChildClickListener = listener
        super.setOnItemChildClickListener { adapter, view, position ->
            onItemChildClickListener?.invoke(this, view, position)
        }
    }

    override fun setOnItemChildLongClickListener(listener: (adapter: IListAdapter<out T>, view: View, position: Int) -> Boolean) {
        onItemChildLongClickListener = listener
        super.setOnItemChildLongClickListener { adapter, view, position ->
            onItemChildLongClickListener?.invoke(this, view, position) ?: false
        }
    }

    override fun setPreLoadNumber(preLoadNumber: Int?, listener: () -> Unit) {
        // TODO 待实现
    }

}