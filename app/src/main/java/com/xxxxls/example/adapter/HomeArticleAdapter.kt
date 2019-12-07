package com.xxxxls.example.adapter

import com.xxxxls.adapter.XSuperAdapter
import com.xxxxls.adapter.XSuperViewHolder
import com.xxxxls.example.R
import com.xxxxls.example.bean.ArticleBean

/**
 * 首页文章
 * @author Max
 * @date 2019-12-07.
 */
class HomeArticleAdapter :
    XSuperAdapter<ArticleBean, XSuperViewHolder>(R.layout.item_home_article_list) {

    override fun convert(helper: XSuperViewHolder, item: ArticleBean?) {
        helper.setText(R.id.tv_title,"")
    }

}