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
import com.xxxxls.adapter.IDiffItemCallback
import com.xxxxls.adapter.XSuperViewHolder

/**
 * super - Adapter
 * @author Max
 * @date 2019-12-07.
 */
abstract class XSuperPagingAdapter<T, VH : XSuperViewHolder> :
    PagedListAdapter<T, VH> {

    //默认布局ID
    @LayoutRes
    protected var mLayoutResId: Int

    //Context
    protected lateinit var mContext: Context

    //LayoutInflater
    protected lateinit var mLayoutInflater: LayoutInflater

    constructor(layoutResId: Int = 0,diffCallback: DiffUtil.ItemCallback<T>) : super(diffCallback) {
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


    protected fun getItemView(@LayoutRes layoutResId: Int, parent: ViewGroup): View {
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
}


