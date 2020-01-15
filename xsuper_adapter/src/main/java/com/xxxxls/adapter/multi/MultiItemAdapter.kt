package com.xxxxls.adapter.multi

import androidx.annotation.LayoutRes

/**
 * 多条目类型Adapter
 * @author Max
 * @date 2020-01-15.
 */
internal interface MultiItemAdapter {

    companion object {
        const val DEFAULT_ITEM_TYPE = -0xff
    }

    /**
     * 添加条目类型
     * @param itemType 类型
     * @param layoutResId 布局ID
     */
    fun addItemType(itemType: Int, @LayoutRes layoutResId: Int)


    /**
     * 获取条目布局ID
     * @param itemType 条目类型
     */
    fun getItemViewLayoutId(itemType: Int): Int
}