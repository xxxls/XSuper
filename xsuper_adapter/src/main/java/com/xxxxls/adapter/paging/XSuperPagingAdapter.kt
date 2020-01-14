package com.xxxxls.adapter.paging

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.NonNull
import androidx.paging.PagedList
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.xxxxls.adapter.IAdapter
import com.xxxxls.adapter.IXSuperAdapter
import com.xxxxls.adapter.XSuperViewHolder
import com.xxxxls.adapter.listener.OnItemChildClickListener
import com.xxxxls.adapter.listener.OnItemChildLongClickListener
import com.xxxxls.adapter.listener.OnItemClickListener
import com.xxxxls.adapter.listener.OnItemLongClickListener
import com.xxxxls.adapter.utils.ClassUtils
import java.lang.Exception

/**
 * super - Adapter
 * @author Max
 * @date 2019-12-07.
 */
abstract class XSuperPagingAdapter<T, VH : XSuperViewHolder> : IXSuperAdapter<T>,
    PagedListAdapter<T, VH> {

    private var mRecyclerView: RecyclerView? = null

    //默认布局ID
    @LayoutRes
    protected var mLayoutResId: Int

    //Context
    protected lateinit var mContext: Context

    //LayoutInflater
    protected lateinit var mLayoutInflater: LayoutInflater

    //条目点击事件
    private var mOnItemClickListener: OnItemClickListener? = null
    //条目子view点击事件
    private var mOnItemChildClickListener: OnItemChildClickListener? = null
    //条目长按事件
    private var mOnItemLongClickListener: OnItemLongClickListener? = null
    //条目子view长按事件
    private var mOnItemChildLongClickListener: OnItemChildLongClickListener? = null


    constructor(
        layoutResId: Int = 0,
        diffCallback: DiffUtil.ItemCallback<T>
    ) : super(diffCallback) {
        this.mLayoutResId = layoutResId
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        mContext = parent.context
        mLayoutInflater = LayoutInflater.from(mContext)
        return createViewHolder(getItemView(mLayoutResId, parent)).apply {
            adapter = this@XSuperPagingAdapter
            bindItemViewListener(this)
        }
    }

    /**
     * createViewHolder
     */
    protected open fun createViewHolder(view: View): VH {
        return try {
            val clazz = ClassUtils.getSuperClassGenericType<VH>(javaClass, 2)
            val constructor = clazz.getDeclaredConstructor(View::class.java)
            constructor.isAccessible = true
            constructor.newInstance(view)
        } catch (e: Exception) {
            XSuperViewHolder(view) as VH
        }
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        convert(holder, getItem(position))
    }

    override fun onBindViewHolder(holder: VH, position: Int, payloads: MutableList<Any>) {
        if (payloads.isNullOrEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
            return
        }
        convertPayloads(holder, getItem(position), payloads)
    }

    protected open fun getItemView(@LayoutRes layoutResId: Int, parent: ViewGroup): View {
        return mLayoutInflater.inflate(layoutResId, parent, false)
    }

    /**
     * 更新UI
     * @param helper
     * @param item
     */
    protected abstract fun convert(@NonNull helper: VH, item: T?)

    /**
     * 更新UI 局部
     * @param helper
     * @param item
     * @param payloads
     */
    protected open fun convertPayloads(
        @NonNull helper: VH, item: T?,
        payloads: MutableList<Any>
    ) {

    }

    override fun getData(): List<T> {
        currentList?.let {
            return it
        } ?: let {
            return emptyList()
        }
    }

    override fun getItemView(position: Int): View? {
        return (mRecyclerView?.findViewHolderForLayoutPosition(position))?.itemView
    }

    override fun getItemChildView(position: Int, viewId: Int): View? {
        return (mRecyclerView?.findViewHolderForLayoutPosition(position) as? XSuperViewHolder)?.getView(
            viewId
        )
    }

    /**
     * 绑定itemView事件
     */
    protected open fun bindItemViewListener(holder: VH) {
        //点击事件
        mOnItemClickListener?.apply {
            holder.itemView.setOnClickListener {
                val position = holder.adapterPosition
                onItemClick(it, position)
            }
        }

        //长按事件
        mOnItemLongClickListener?.apply {
            holder.itemView.setOnLongClickListener {
                val position = holder.adapterPosition
                onItemLongClick(it, position)
            }
        }
    }


    override fun addData(newData: T, position: Int?) {
        submitList(currentList?.apply {
            add(position ?: size, newData)
        })
    }

    override fun addData(newData: List<T>, position: Int?) {
        submitList(currentList?.apply {
            addAll(position ?: size, newData)
        })
    }

    override fun remove(position: Int) {
        submitList(currentList?.apply {
            removeAt(position)
        })
    }

    override fun removeAll() {
        submitList(currentList?.apply {
            clear()
        })
    }

    override fun replaceData(data: List<T>) {
        submitList(currentList?.apply {
            clear()
            addAll(data)
        })
    }

    override fun getItem(position: Int): T? {
        return super.getItem(position)
    }

    /**
     * 设置点击事件
     */
    fun setOnItemClickListener(listener: OnItemClickListener?) {
        this.mOnItemClickListener = listener
    }

    /**
     * 设置长按事件
     */
    fun setOnItemLongClickListener(listener: OnItemLongClickListener?) {
        this.mOnItemLongClickListener = listener
    }

    /**
     * 设置子view点击事件
     */
    fun setOnItemChildClickListener(listener: OnItemChildClickListener?) {
        this.mOnItemChildClickListener = listener
    }

    /**
     * 设置子view长按事件
     */
    fun setOnItemChildLongClickListener(listener: OnItemChildLongClickListener?) {
        this.mOnItemChildLongClickListener = listener
    }

    override fun getOnItemClickListener(): OnItemClickListener? {
        return mOnItemClickListener
    }

    override fun getOnItemLongClickListener(): OnItemLongClickListener? {
        return mOnItemLongClickListener
    }

    override fun getOnItemChildClickListener(): OnItemChildClickListener? {
        return mOnItemChildClickListener
    }

    override fun getOnItemChildLongClickListener(): OnItemChildLongClickListener? {
        return mOnItemChildLongClickListener
    }
}


