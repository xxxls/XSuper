package com.xxxxls.example.adapter

import com.xxxxls.adapter.XSuperAdapter
import com.xxxxls.adapter.XSuperViewHolder
import com.xxxxls.example.R
import com.xxxxls.example.bean.ArticleBean
import com.xxxxls.utils.L

/**
 * 首页文章
 * @author Max
 * @date 2019-12-07.
 */
class HomeArticleAdapter :
    XSuperAdapter<ArticleBean, XSuperViewHolder>(R.layout.item_paging_list) {

    override fun convertPayloads(
        helper: XSuperViewHolder,
        item: ArticleBean?,
        payloads: MutableList<Any>
    ) {
        if (payloads.contains("author")) {
            helper.setText(R.id.tv_author, (item?.author ?: "") + " -Update")
        }
        L.e("convertPayloads() payloads: $payloads")
    }

    override fun convert(helper: XSuperViewHolder, item: ArticleBean?) {
        helper.setText(R.id.tv_title, item?.title ?: "")
        helper.setText(R.id.tv_author, item?.author ?: "")
    }

    override fun areItemsTheSame(oldItem: ArticleBean, newItem: ArticleBean): Boolean {
        return newItem == oldItem
    }

    override fun areContentsTheSame(oldItem: ArticleBean, newItem: ArticleBean): Boolean {
        return newItem.author == oldItem.author
    }

    override fun getChangePayload(oldItem: ArticleBean, newItem: ArticleBean): Any? {
        if (newItem.author != oldItem.author) {
            return "title"
        }
        return super.getChangePayload(oldItem, newItem)
    }
}