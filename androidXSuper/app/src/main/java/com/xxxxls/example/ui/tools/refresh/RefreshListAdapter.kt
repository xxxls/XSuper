package com.xxxxls.example.ui.tools.refresh

import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.xxxxls.example.R
import com.xxxxls.example.bean.TestPagingBean
import com.xxxxls.module_base.adapter.BaseListAdapter

/**
 * 刷新列表-adapter
 * @author Max
 * @date 1/28/21.
 */
class RefreshListAdapter :
    BaseListAdapter<TestPagingBean, BaseViewHolder>(R.layout.item_paging_list) {
    override fun convert(holder: BaseViewHolder, item: TestPagingBean) {
        holder.setText(R.id.tv_title, item.title)
        holder.setText(R.id.tv_content, item.content)
    }
}