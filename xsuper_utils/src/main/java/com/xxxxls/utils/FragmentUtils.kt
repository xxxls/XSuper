package com.xxxxls.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager


/**
 * Fragment工具类
 * @author Max
 * @date 2020-01-07.
 */
object FragmentUtils {

    /**
     * 添加Fragment
     * @param fragment
     * @param containerViewId
     */
    fun addFragment(
        fragmentManager: FragmentManager,
        fragment: Fragment,
        containerViewId: Int,
        tag: String? = fragment.javaClass.name
    ) {
        if (fragment.isAdded) return
        fragmentManager.beginTransaction().add(containerViewId, fragment, tag)
            .commitAllowingStateLoss()
    }


    /**
     * 移除Fragment
     * @param fragment
     */
    fun removeFragment(
        fragmentManager: FragmentManager,
        fragment: Fragment
    ) {
        if (!fragment.isAdded) {
            return
        }
        fragmentManager.beginTransaction().remove(fragment)
            .commitAllowingStateLoss()
    }

    /**
     * 展示并添加Fragment
     * @param fragment
     * @param containerViewId
     */
    fun showFragment(
        fragmentManager: FragmentManager,
        fragment: Fragment,
        containerViewId: Int,
        tag: String? = fragment.javaClass.name
    ) {
        fragmentManager.beginTransaction().apply {
            if (!fragment.isAdded) {
                add(containerViewId, fragment, tag)
            }
            fragmentManager.fragments.filter { it != fragment }.forEach {
                hide(it)
            }
            show(fragment)
            commitAllowingStateLoss()
        }
    }

    /**
     * 隐藏Fragment
     * @param fragment
     */
    fun hideFragment(
        fragmentManager: FragmentManager,
        fragment: Fragment
    ) {
        if (fragment.isAdded && !fragment.isHidden) {
            fragmentManager.beginTransaction().apply {
                hide(fragment)
                commitAllowingStateLoss()
            }
        }
    }

    /**
     * 替换Fragment
     * @param fragment
     * @param containerViewId
     */
    fun replaceFragment(
        fragmentManager: FragmentManager,
        fragment: Fragment,
        containerViewId: Int,
        tag: String? = fragment.javaClass.name
    ) {
        fragmentManager.beginTransaction().replace(containerViewId, fragment, tag)
            .commitAllowingStateLoss()
    }
}