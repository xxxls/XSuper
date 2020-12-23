package com.xxxxls.example.ui.paging.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.paging.PagingDataAdapter
import com.xxxxls.example.R
import com.xxxxls.example.ui.paging.data.bean.PagingItemBean

/**
 * 分页 Adapter
 * @author Max
 * @date 12/23/20.
 */
class PagingListAdapter :
    PagingDataAdapter<PagingItemBean, ArticleItemViewHolder>(PagingItemBean.newDiffCallback()) {
    override fun onBindViewHolder(holder: ArticleItemViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bindData(it, position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleItemViewHolder {
        return ArticleItemViewHolder(
            inflateView(
                parent,
                R.layout.item_home_article
            )
        )
    }

    private fun inflateView(viewGroup: ViewGroup, @LayoutRes viewType: Int): View {
        val layoutInflater = LayoutInflater.from(viewGroup.context)
        return layoutInflater.inflate(viewType, viewGroup, false)
    }
}