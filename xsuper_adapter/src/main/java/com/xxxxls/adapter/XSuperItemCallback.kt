package com.xxxxls.adapter

import androidx.recyclerview.widget.DiffUtil

/**
 * DiffUtil ItemCallback
 * @author Max
 * @date 2019-12-07.
 */
class XSuperItemCallback<T> : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }
}