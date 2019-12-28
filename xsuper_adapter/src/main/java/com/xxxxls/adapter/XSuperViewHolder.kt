package com.xxxxls.adapter

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Build
import android.util.SparseArray
import android.util.TypedValue
import android.view.View
import android.webkit.WebSettings
import android.widget.Checkable
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView

/**
 * ViewHolder
 * @author Max
 * @date 2019-12-07.
 */
open class XSuperViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    //Adapter
    var adapter: IXSuperAdapter<*>? = null

    /**
     * view集合
     */
    private val views: SparseArray<View> by lazy {
        SparseArray<View>()
    }

    /**
     * 添加view点击事件
     */
    protected open fun addOnClickListener(@IdRes vararg viewIds: Int) {
        viewIds.forEach {
            getView<View>(it)?.apply {
                isClickable = true
                setOnClickListener { view ->
                    adapter?.getOnItemChildClickListener()
                        ?.onItemChildClick(adapter!!, view, adapterPosition)
                }
            }
        }
    }

    /**
     * 添加view长按点击事件
     */
    protected open fun addOnLongClickListener(@IdRes vararg viewIds: Int) {
        viewIds.forEach {
            getView<View>(it)?.apply {
                isClickable = true
                setOnLongClickListener { view ->
                    return@setOnLongClickListener adapter?.getOnItemChildLongClickListener()
                        ?.onItemChildLongClick(adapter!!, view, adapterPosition) ?: false
                }
            }
        }
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
     * 设置文本颜色
     */
    fun setTextColor(@IdRes viewId: Int, @ColorInt color: Int) {
        getView<TextView>(viewId)?.setTextColor(color)
    }

    /**
     * 设置文本大小
     */
    fun setTextSize(@IdRes viewId: Int, size: Float, unit: Int = TypedValue.COMPLEX_UNIT_SP) {
        getView<TextView>(viewId)?.setTextSize(unit, size)
    }

    /**
     * 设置背景颜色
     */
    fun setBackgroundColor(@IdRes viewId: Int, @ColorInt color: Int) {
        getView<View>(viewId)?.setBackgroundColor(color)
    }

    /**
     * 设置背景资源
     */
    fun setBackgroundResource(@IdRes viewId: Int, @DrawableRes resid: Int) {
        getView<View>(viewId)?.setBackgroundResource(resid)
    }

    /**
     * 设置图片资源
     */
    fun setImageResource(@IdRes viewId: Int, @DrawableRes resid: Int) {
        getView<ImageView>(viewId)?.setImageResource(resid)
    }

    /**
     * 设置图片资源
     */
    fun setImageDrawable(@IdRes viewId: Int, drawable: Drawable) {
        getView<ImageView>(viewId)?.setImageDrawable(drawable)
    }

    /**
     * 设置图片资源
     */
    fun setImageBitmap(@IdRes viewId: Int, bitmap: Bitmap) {
        getView<ImageView>(viewId)?.setImageBitmap(bitmap)
    }

    /**
     * 设置进度
     */
    fun setProgress(@IdRes viewId: Int, progress: Int? = null, max: Int? = null, min: Int? = null) {
        getView<ProgressBar>(viewId)?.apply {
            progress?.let {
                this.progress = it
            }

            max?.let {
                this.max = it
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                min?.let {
                    this.min = it
                }
            }
        }
    }

    /**
     * 设置Tag
     * @param viewId
     * @param tag
     * @param key
     */
    fun setTag(@IdRes viewId: Int, tag: Any?, key: Int? = null) {
        key?.let {
            getView<View>(viewId)?.setTag(it, tag)
        } ?: let {
            getView<View>(viewId)?.setTag(tag)
        }
    }

    /**
     * 设置可见性
     */
    fun setVisibility(@IdRes viewId: Int, visibility: Int) {
        getView<View>(viewId)?.visibility = visibility
    }

    /**
     * 设置enabled
     */
    fun setEnabled(@IdRes viewId: Int, enabled: Boolean) {
        getView<View>(viewId)?.isEnabled = enabled
    }

    /**
     * 设置checked
     */
    fun setChecked(@IdRes viewId: Int, checked: Boolean) {
        (getView<View>(viewId) as? Checkable)?.isChecked = checked
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