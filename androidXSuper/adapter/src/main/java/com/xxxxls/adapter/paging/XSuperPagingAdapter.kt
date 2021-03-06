package com.xxxxls.adapter.paging

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.xxxxls.adapter.*
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
    XSuperAdapterEngine<T, VH>,
    PagedListAdapter<T, VH> {

    private var mRecyclerView: RecyclerView? = null

    //默认布局ID
    @LayoutRes
    protected open var mLayoutResId: Int? = null

    //Context
    protected open var mContext: Context? = null

    //LayoutInflater
    protected open var mLayoutInflater: LayoutInflater? = null

    //条目点击事件
    protected open var mOnItemClickListener: OnItemClickListener? = null
    //条目子view点击事件
    protected open var mOnItemChildClickListener: OnItemChildClickListener? = null
    //条目长按事件
    protected open var mOnItemLongClickListener: OnItemLongClickListener? = null
    //条目子view长按事件
    protected open var mOnItemChildLongClickListener: OnItemChildLongClickListener? = null

    constructor(diffCallback: DiffUtil.ItemCallback<T>) : super(diffCallback)

    constructor(
        layoutResId: Int = 0,
        diffCallback: DiffUtil.ItemCallback<T>
    ) : super(diffCallback) {
        this.mLayoutResId = layoutResId
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        if (mContext == null) {
            mContext = parent.context
            mLayoutInflater = LayoutInflater.from(parent.context)
        }
        return xCreateViewHolder(parent, viewType)
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

    /**
     * createViewHolder
     */
    override fun createSuperViewHolder(view: View): VH {
        return try {
            val clazz = ClassUtils.getSuperClassGenericType<VH>(javaClass, 2)
            val constructor = clazz.getDeclaredConstructor(View::class.java)
            constructor.isAccessible = true
            constructor.newInstance(view)
        } catch (e: Exception) {
            XSuperViewHolder(view) as VH
        }
    }

    override fun getItemView(@LayoutRes layoutResId: Int, parent: ViewGroup): View {
        return mLayoutInflater!!.inflate(layoutResId, parent, false)
    }

    override fun getXSuperAdapter(): IXSuperAdapter<T> {
        return this
    }

    override fun getItemViewLayoutId(itemType: Int): Int {
        checkNotNull(mLayoutResId)
        return mLayoutResId!!
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

    override fun getRecyclerViewAdapter(): RecyclerView.Adapter<*> {
        return this
    }

    override fun getItem(position: Int): T? {
        return super.getItem(position)
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

    /**
     * 设置点击事件
     */
    override fun setOnItemClickListener(listener: OnItemClickListener?) {
        this.mOnItemClickListener = listener
    }

    /**
     * 设置长按事件
     */
    override fun setOnItemLongClickListener(listener: OnItemLongClickListener?) {
        this.mOnItemLongClickListener = listener
    }

    /**
     * 设置子view点击事件
     */
    override fun setOnItemChildClickListener(listener: OnItemChildClickListener?) {
        this.mOnItemChildClickListener = listener
    }

    /**
     * 设置子view长按事件
     */
    override fun setOnItemChildLongClickListener(listener: OnItemChildLongClickListener?) {
        this.mOnItemChildLongClickListener = listener
    }

}


