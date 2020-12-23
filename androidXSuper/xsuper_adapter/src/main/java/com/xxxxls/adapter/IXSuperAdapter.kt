package com.xxxxls.adapter

import android.view.View
import com.xxxxls.adapter.listener.*

/**
 * XSuper Adapter
 * @author Max
 * @date 2019-12-28.
 */
interface IXSuperAdapter<T> : IAdapter<T>{

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

    /**
     * 设置点击事件
     */
    fun setOnItemClickListener(listener: OnItemClickListener?)

    /**
     * 设置长按事件
     */
    fun setOnItemLongClickListener(listener: OnItemLongClickListener?)

    /**
     * 设置子view点击事件
     */
    fun setOnItemChildClickListener(listener: OnItemChildClickListener?)

    /**
     * 设置子view长按事件
     */
    fun setOnItemChildLongClickListener(listener: OnItemChildLongClickListener?)

    override fun setOnItemClickListener(listener: (adapter: IAdapter<out T>, view: View, position: Int) -> Unit) {
        setOnItemClickListener(listener = object : OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                listener(this@IXSuperAdapter, view, position)
            }
        })
    }

    override fun setOnItemLongClickListener(listener: (adapter: IAdapter<out T>, view: View, position: Int) -> Boolean) {
        setOnItemLongClickListener(listener = object : OnItemLongClickListener {
            override fun onItemLongClick(view: View, position: Int): Boolean {
                return listener(this@IXSuperAdapter, view, position)
            }
        })
    }

    override fun setOnItemChildClickListener(listener: (adapter: IAdapter<out T>, view: View, position: Int) -> Unit) {
        setOnItemChildClickListener(listener = object : OnItemChildClickListener {
            override fun onItemChildClick(view: View, position: Int) {
                listener(this@IXSuperAdapter, view, position)
            }
        })
    }

    override fun setOnItemChildLongClickListener(listener: (adapter: IAdapter<out T>, view: View, position: Int) -> Boolean) {
        setOnItemChildLongClickListener(listener = object : OnItemChildLongClickListener {
            override fun onItemChildLongClick(view: View, position: Int): Boolean {
                return listener(this@IXSuperAdapter, view, position)
            }
        })
    }

}