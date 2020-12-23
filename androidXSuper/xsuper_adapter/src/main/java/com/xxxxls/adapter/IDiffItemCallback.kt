package com.xxxxls.adapter

import android.util.Log


/**
 * DiffUtil.ItemCallback
 * @author Max
 * @date 2019-12-12.
 */
interface IDiffItemCallback<T> {

    fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        Log.e("IDiffItemCallback", "areItemsTheSame()")
        return oldItem == newItem
    }

    fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        Log.e("IDiffItemCallback", "areContentsTheSame()")
        return oldItem == newItem
    }

    fun getChangePayload(oldItem: T, newItem: T): Any? {
        Log.e("IDiffItemCallback", "getChangePayload()")
        return null
    }
}