package com.xxxxls.adapter

import android.util.SparseArray
import android.view.View
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView

/**
 * ViewHolder
 * @author Max
 * @date 2019-12-07.
 */
class XSuperViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    /**
     * view集合
     */
    private val views: SparseArray<View> by lazy {
        SparseArray<View>()
    }


    /**
     * 设置Text
     * @param viewId ID
     * @param value 设置的值
     * @return this
     */
    fun setText(@IdRes viewId: Int, value: CharSequence): XSuperViewHolder {
        getView<TextView>(viewId)?.text = value
        return this
    }

    /**
     * 设置Text
     * @param viewId ID
     * @param strId 设置的值ID
     * @return this
     */
    fun setText(@IdRes viewId: Int, @StringRes strId: Int): XSuperViewHolder {
        getView<TextView>(viewId)?.setText(strId)
        return this
    }


    /**
     * 获取某个子view
     * @param viewId
     * @return view
     */
    fun <T : View> getView(@IdRes viewId: Int): T? {
        var view: View? = views.get(viewId)
        if (view == null) {
            view = itemView.findViewById(viewId)
            views.put(viewId, view)
        }
        return view as? T
    }

}