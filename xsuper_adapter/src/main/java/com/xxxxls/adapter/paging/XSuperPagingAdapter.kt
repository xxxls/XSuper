package com.xxxxls.adapter.paging

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import com.xxxxls.adapter.DefaultItemCallback
import com.xxxxls.adapter.IAdapter
import com.xxxxls.adapter.IDiffItemCallback
import com.xxxxls.adapter.XSuperViewHolder
import com.xxxxls.adapter.listener.OnItemChildClickListener
import com.xxxxls.adapter.listener.OnItemClickListener

/**
 * super - Adapter
 * @author Max
 * @date 2019-12-07.
 */
abstract class XSuperPagingAdapter<T, VH : XSuperViewHolder> : IAdapter<T>,
    PagedListAdapter<T, VH> {

    //默认布局ID
    @LayoutRes
    protected var mLayoutResId: Int

    //Context
    protected lateinit var mContext: Context

    //LayoutInflater
    protected lateinit var mLayoutInflater: LayoutInflater

    //条目点击事件
    var mOnItemClickListener: OnItemClickListener? = null
    //条目子view点击事件
    var mOnItemChildClickListener: OnItemChildClickListener? = null


    constructor(
        layoutResId: Int = 0,
        diffCallback: DiffUtil.ItemCallback<T>
    ) : super(diffCallback) {
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
        return null
    }

    override fun addData(newData: T, position: Int?) {
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


