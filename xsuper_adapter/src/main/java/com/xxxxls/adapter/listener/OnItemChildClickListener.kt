package com.xxxxls.adapter.listener

import android.view.View
import com.xxxxls.adapter.IAdapter

/**
 * 条目子view点击事件
 * @author Max
 * @date 2019-12-27.
 */
interface OnItemChildClickListener {

    fun onItemChildClick(adapter: IAdapter<*>, view: View, position: Int)
}