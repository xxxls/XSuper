package com.xxxxls.example.ui.paging.data.bean

import androidx.recyclerview.widget.DiffUtil

/**
 * 分页条目
 * @author Max
 * @date 2020/12/23.
 */
data class PagingItemBean(val id: Int, val title: String?) {
    companion object {
        fun newDiffCallback() = object : DiffUtil.ItemCallback<PagingItemBean>() {
            override fun areItemsTheSame(
                oldItem: PagingItemBean,
                newItem: PagingItemBean
            ): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: PagingItemBean,
                newItem: PagingItemBean
            ): Boolean =
                oldItem == newItem
        }
    }
}