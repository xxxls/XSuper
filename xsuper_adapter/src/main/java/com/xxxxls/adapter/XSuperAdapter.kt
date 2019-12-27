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

/**
 * super - Adapter
 * @author Max
 * @date 2019-12-07.
 */
 abstract class XSuperAdapter<T, VH : XSuperViewHolder> :IAdapter<T>,
    RecyclerView.Adapter<VH>, IDiffItemCallback<T> {

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

    constructor(layoutResId: Int = 0) : super() {
        this.mLayoutResId = layoutResId
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        mContext = parent.context
        mLayoutInflater = LayoutInflater.from(mContext)
        return XSuperViewHolder(
            getItemView(mLayoutResId, parent)
        ) as VH
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

    override fun getData(): List<T> {
        return mDiffer.currentList
    }

    override fun getItemView(position: Int): View? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addData(newData: T, position: Int?) {
//        mDiffer.submitList()
    }

    override fun addData(newData: List<T>, position: Int?) {
    }

    override fun remove(position: Int) {
    }

    override fun removeAll() {
    }

    override fun replaceData(data: List<T>) {
    }
}


