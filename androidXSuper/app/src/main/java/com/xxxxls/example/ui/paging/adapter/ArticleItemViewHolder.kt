package com.xxxxls.example.ui.paging.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.xxxxls.example.R
import com.xxxxls.example.ui.paging.data.bean.PagingItemBean

/**
 * @author Max
 * @date 12/23/20.
 */
class ArticleItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    fun bindData(item: PagingItemBean, position: Int) {
        view.findViewById<TextView>(R.id.tv_title)?.text = item.title ?: ""
    }
}