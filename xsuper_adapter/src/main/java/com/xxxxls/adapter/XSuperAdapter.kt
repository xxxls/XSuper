package com.xxxxls.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.NonNull
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil

/**
 * super - Adapter
 * @author Max
 * @date 2019-12-07.
 */
abstract class XSuperAdapter<T, VH : XSuperViewHolder> :
    PagedListAdapter<T, VH> {

    //默认布局ID
    @LayoutRes
    protected var mLayoutResId: Int

    protected lateinit var mContext: Context
    protected lateinit var mLayoutInflater: LayoutInflater

    constructor(
        layoutResId: Int,
        diffCallback: DiffUtil.ItemCallback<T> = XSuperItemCallback()
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

    protected fun getItemView(@LayoutRes layoutResId: Int, parent: ViewGroup): View {
        return mLayoutInflater.inflate(layoutResId, parent, false)
    }


    /**
     * Implement this method and use the helper to adapt the view to the given item.
     *
     * @param helper A fully initialized helper.
     * @param item   The item that needs to be displayed.
     */
    protected abstract fun convert(@NonNull helper: VH, item: T?)

}


