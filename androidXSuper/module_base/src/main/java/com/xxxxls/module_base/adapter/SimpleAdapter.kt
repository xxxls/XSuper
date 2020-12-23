package com.xxxxls.module_base.adapter

import com.xxxxls.adapter.XSuperAdapter
import com.xxxxls.adapter.XSuperViewHolder

/**
 * 简单的adapter示例
 * @author Max
 * @date 2019-12-16.
 */
class SimpleAdapter : XSuperAdapter<String, XSuperViewHolder>(android.R.layout.simple_list_item_2) {

    override fun convert(helper: XSuperViewHolder, item: String?) {
        helper.setText(android.R.id.text1, item ?: "Item: ${helper.adapterPosition}")
        helper.setText(android.R.id.text2, "position: ${helper.adapterPosition}")
    }

}