package com.xxxxls.example.adapter

import com.xxxxls.adapter.XSuperAdapter
import com.xxxxls.adapter.XSuperViewHolder
import com.xxxxls.example.R
import com.xxxxls.example.bean.SimpleItemBean

/**
 * 简单的 list 条目
 * @author Max
 * @date 2020-01-07.
 */
class SimpleListItemAdapter : XSuperAdapter<SimpleItemBean, XSuperViewHolder>(R.layout.item_simple_list) {
    override fun convert(helper: XSuperViewHolder, item: SimpleItemBean?) {
        helper.setText(R.id.tv_title, item?.title)
    }
}