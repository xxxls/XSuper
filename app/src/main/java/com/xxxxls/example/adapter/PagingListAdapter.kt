package com.xxxxls.example.adapter

import androidx.recyclerview.widget.DiffUtil
import com.xxxxls.adapter.XSuperViewHolder
import com.xxxxls.adapter.paging.XSuperPagingAdapter
import com.xxxxls.example.R
import com.xxxxls.example.bean.ArticleBean
import com.xxxxls.example.bean.TestPagingBean
import com.xxxxls.utils.L

/**
 * 测试adapter
 * @author Max
 * @date 2019-12-07.
 */
class PagingListAdapter :
    XSuperPagingAdapter<TestPagingBean, XSuperViewHolder>(
        R.layout.item_paging_list,
        DiffCallback()
    ) {

    override fun convertPayloads(
        helper: XSuperViewHolder,
        item: TestPagingBean?,
        payloads: MutableList<Any>
    ) {
        if (payloads.contains("content")) {
            helper.setText(R.id.tv_content, (item?.content ?: "") + " -Update")
        }
        L.e("convertPayloads() payloads: $payloads")
    }

    override fun convert(helper: XSuperViewHolder, item: TestPagingBean?) {
        helper.setText(R.id.tv_title, item?.title ?: "")
        helper.setText(R.id.tv_content, item?.content ?: "")
    }
}

class DiffCallback : DiffUtil.ItemCallback<TestPagingBean>() {
    override fun areItemsTheSame(oldItem: TestPagingBean, newItem: TestPagingBean): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TestPagingBean, newItem: TestPagingBean): Boolean {
        return oldItem.id == newItem.id
    }

}