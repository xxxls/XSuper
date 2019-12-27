package com.xxxxls.adapter.listener

import android.view.View
import com.xxxxls.adapter.IAdapter

/**
 * item 点击事件
 * @author Max
 * @date 2019-12-27.
 */
interface OnItemClickListener {
    fun onItemClick(adapter: IAdapter<*>, view: View, position: Int)
}