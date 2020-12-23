package com.xxxxls.adapter.multi

import android.util.SparseIntArray


/**
 * 多条目类型Adapter
 * @author Max
 * @date 2020-01-15.
 */
internal class XSuperMultiItemAdapter : MultiItemAdapter {

    private val layouts: SparseIntArray by lazy {
        SparseIntArray()
    }

    override fun addItemType(itemType: Int, layoutResId: Int) {
        layouts.put(itemType, layoutResId)
    }

    override fun getItemViewLayoutId(itemType: Int): Int {
        return layouts.get(itemType)
    }
}