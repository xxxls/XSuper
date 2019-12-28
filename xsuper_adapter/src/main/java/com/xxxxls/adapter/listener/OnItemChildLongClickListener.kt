package com.xxxxls.adapter.listener

import android.view.View
import com.xxxxls.adapter.IAdapter

/**
 * 条目子view长按事件
 * @author Max
 * @date 2019-12-28.
 */
interface OnItemChildLongClickListener {

    /**
     * 子view长按事件
     */
    fun onItemChildLongClick(
        adapter: IAdapter<*>,
        view: View,
        position: Int
    ): Boolean
}