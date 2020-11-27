package com.xxxxls.xsuper.component

import android.content.Context
import android.view.View
import androidx.annotation.IdRes
import androidx.lifecycle.LifecycleOwner


/**
 * 基础组件（activity，fragment，dialog）
 * @author Max
 * @date 2019-11-26.
 */
interface IComponent {

    fun getContext(): Context?

    /**
     * 获取Lifecycle
     */
    fun getLifecycleOwner(): LifecycleOwner?

    /**
     * 查找子View
     */
    fun <T : View> findViewById(@IdRes id: Int): T?

}