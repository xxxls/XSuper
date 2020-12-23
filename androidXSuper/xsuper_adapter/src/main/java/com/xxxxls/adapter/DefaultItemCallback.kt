package com.xxxxls.adapter

import androidx.recyclerview.widget.DiffUtil

/**
 * DiffUtil.ItemCallbac
 * @author Max
 * @date 2019-12-12.
 */
class DefaultItemCallback<T>(private val diffItemCallback: IDiffItemCallback<T>) :
    DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        //两个条目是否相同
        return diffItemCallback.areItemsTheSame(oldItem, newItem)
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        //两个相同条目内容是否相同
        return diffItemCallback.areContentsTheSame(oldItem, newItem)
    }

    override fun getChangePayload(oldItem: T, newItem: T): Any? {
        return diffItemCallback.getChangePayload(oldItem, newItem)
    }
}