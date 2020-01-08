package com.xxxxls.adapter.listener

import android.view.View
import com.xxxxls.adapter.IAdapter

/**
 * 列表长按事件
 * @author Max
 * @date 2019-12-28.
 */
interface OnItemLongClickListener {

    /**
     * 列表条目长按事件
     * @param view 条目View
     * @param position 在列表中的位置
     */
    fun onItemLongClick(
        view: View,
        position: Int
    ): Boolean
}