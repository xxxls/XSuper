package com.xxxxls.example.adapter

import com.xxxxls.adapter.XSuperAdapter
import com.xxxxls.adapter.XSuperViewHolder
import com.xxxxls.example.R
import com.xxxxls.example.bean.IndexItemBean

/**
 * Index 条目
 * @author Max
 * @date 2020-01-07.
 */
class IndexItemAdapter : XSuperAdapter<IndexItemBean, XSuperViewHolder>(R.layout.item_index) {
    override fun convert(helper: XSuperViewHolder, item: IndexItemBean?) {
        helper.setText(R.id.tv_title, item?.title)
    }
}