package com.xxxxls.adapter.listener

import android.view.View
import com.xxxxls.adapter.IAdapter

/**
 * 条目子view点击事件
 * @author Max
 * @date 2019-12-27.
 */
interface OnItemChildClickListener {

    /**
     * 子 view点击事件
     */
    fun onItemChildClick(view: View, position: Int)
}