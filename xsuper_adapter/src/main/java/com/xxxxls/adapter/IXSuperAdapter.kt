package com.xxxxls.adapter

import com.xxxxls.adapter.listener.OnItemChildClickListener
import com.xxxxls.adapter.listener.OnItemChildLongClickListener
import com.xxxxls.adapter.listener.OnItemClickListener
import com.xxxxls.adapter.listener.OnItemLongClickListener

/**
 * XSuper Adapter
 * @author Max
 * @date 2019-12-28.
 */
interface IXSuperAdapter<T> : IAdapter<T> {

    /**
     * 条目点击事件
     */
    fun getOnItemClickListener(): OnItemClickListener?

    /**
     * 条目长按事件
     */
    fun getOnItemLongClickListener(): OnItemLongClickListener?

    /**
     * 子view点击事件
     */
    fun getOnItemChildClickListener(): OnItemChildClickListener?

    /**
     * 子view长按事件
     */
    fun getOnItemChildLongClickListener(): OnItemChildLongClickListener?
}