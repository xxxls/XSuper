package com.xxxxls.adapter

import com.xxxxls.adapter.multi.MultiItemAdapter
import com.xxxxls.adapter.multi.MultiItemAdapter.Companion.DEFAULT_ITEM_TYPE
import com.xxxxls.adapter.multi.MultiItemEntity
import com.xxxxls.adapter.multi.XSuperMultiItemAdapter

/**
 * 多类型条目Adapter
 * @author Max
 * @date 2020-01-15.
 */
abstract class XSuperMultiItemAdapter<T : MultiItemEntity, VH : XSuperViewHolder> :
    XSuperAdapter<T, VH>(), MultiItemAdapter by XSuperMultiItemAdapter() {

    init {
        mLayoutResId?.let {
            addItemType(DEFAULT_ITEM_TYPE, it)
        }
    }
}