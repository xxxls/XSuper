package com.xxxxls.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.xxxxls.adapter.listener.OnItemChildClickListener
import com.xxxxls.adapter.listener.OnItemChildLongClickListener
import com.xxxxls.adapter.listener.OnItemClickListener
import com.xxxxls.adapter.listener.OnItemLongClickListener
import com.xxxxls.utils.getStatusBarHeight

/**
 * super - Adapter
 * @author Max
 * @date 2019-12-07.
 */
abstract class XSuperAdapter<T, VH : XSuperViewHolder> : IXSuperAdapter<T>,
    RecyclerView.Adapter<VH>, IDiffItemCallback<T> {


    private var mRecyclerView: RecyclerView? = null

    //默认布局ID
    @LayoutRes
    protected var mLayoutResId: Int

    //Context
    protected lateinit var mContext: Context

    //AsyncListDiffer
    protected val mDiffer: AsyncListDiffer<T> by lazy {
        AsyncListDiffer<T>(this, getDiffUtilItemCallback())
    }

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

    constructor(layoutResId: Int = 0) : super() {
        this.mLayoutResId = layoutResId
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        mContext = parent.context
        mLayoutInflater = LayoutInflater.from(mContext)
        return (XSuperViewHolder(
            getItemView(mLayoutResId, parent)
        ) as VH).apply {
            adapter = this@XSuperAdapter
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

    override fun getItemCount(): Int {
        return mDiffer.currentList.size
    }

    protected open fun getItem(position: Int): T? {
        return mDiffer.currentList[position]
    }

    protected open fun getItemView(@LayoutRes layoutResId: Int, parent: ViewGroup): View {
        return mLayoutInflater.inflate(layoutResId, parent, false)
    }

    /**
     * 设置数据
     */
    fun submitList(@Nullable newList: List<T>) {
        mDiffer.submitList(newList)
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

    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return super.areItemsTheSame(oldItem, newItem)
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return super.areContentsTheSame(oldItem, newItem)
    }

    override fun getChangePayload(oldItem: T, newItem: T): Any? {
        return super.getChangePayload(oldItem, newItem)
    }

    //构建ItemCallback
    open fun getDiffUtilItemCallback(): DiffUtil.ItemCallback<T> {
        return DefaultItemCallback(this)
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

    //条目点击事件
    protected open fun onItemClick(view: View, position: Int) {
        mOnItemClickListener?.onItemClick(this, view, position)
    }

    //条目长按事件
    protected open fun onItemLongClick(view: View, position: Int): Boolean {
        return mOnItemLongClickListener?.onItemLongClick(this, view, position) ?: false
    }

    override fun getData(): List<T> {
        return mDiffer.currentList
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
        mDiffer.currentList.add(position ?: mDiffer.currentList.size, newData)
        mDiffer.submitList(mDiffer.currentList)
    }

    override fun addData(newData: List<T>, position: Int?) {
        mDiffer.currentList.addAll(position ?: mDiffer.currentList.size, newData)
        mDiffer.submitList(mDiffer.currentList)
    }

    override fun remove(position: Int) {
        mDiffer.currentList.removeAt(position)
        mDiffer.submitList(mDiffer.currentList)
    }

    override fun removeAll() {
        mDiffer.currentList.clear()
        mDiffer.submitList(mDiffer.currentList)
    }

    override fun replaceData(data: List<T>) {
        mDiffer.currentList.clear()
        mDiffer.currentList.addAll(data)
        mDiffer.submitList(mDiffer.currentList)
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


