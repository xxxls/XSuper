package com.xxxxls.adapter.paging

import androidx.recyclerview.widget.DiffUtil
import com.xxxxls.adapter.XSuperViewHolder
import com.xxxxls.adapter.multi.MultiItemAdapter
import com.xxxxls.adapter.multi.MultiItemEntity
import com.xxxxls.adapter.multi.XSuperMultiItemAdapter

/**
 * 多类型条目Adapter
 * @author Max
 * @date 2020-01-15.
 */
abstract class XSuperMultiItemPagingAdapter<T : MultiItemEntity, VH : XSuperViewHolder>(diffCallback: DiffUtil.ItemCallback<T>) :
    XSuperPagingAdapter<T, VH>(diffCallback), MultiItemAdapter by XSuperMultiItemAdapter() {

    init {
        mLayoutResId?.let {
            addItemType(MultiItemAdapter.DEFAULT_ITEM_TYPE, it)
        }
    }
}