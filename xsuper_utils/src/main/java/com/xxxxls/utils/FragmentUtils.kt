package com.xxxxls.utils

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager


//展示Fragment
fun FragmentActivity.showFragment(
    fragment: Fragment,
    containerViewId: Int,
    tag: String? = fragment.javaClass.name
) {
    FragmentUtils.showFragment(supportFragmentManager, fragment, containerViewId, tag)
}

//展示Fragment
fun Fragment.showFragment(
    fragment: Fragment,
    containerViewId: Int,
    tag: String? = fragment.javaClass.name
) {
    FragmentUtils.showFragment(childFragmentManager, fragment, containerViewId, tag)
}


//移除Fragment
fun FragmentActivity.removeFragment(
    fragment: Fragment
) {
    FragmentUtils.removeFragment(supportFragmentManager, fragment)
}


//移除Fragment
fun Fragment.removeFragment(
    fragment: Fragment
) {
    FragmentUtils.removeFragment(childFragmentManager, fragment)
}


//添加Fragment
fun FragmentActivity.addFragment(
    fragment: Fragment,
    containerViewId: Int,
    tag: String? = fragment.javaClass.name
) {
    FragmentUtils.addFragment(supportFragmentManager, fragment, containerViewId, tag)
}


//添加Fragment
fun Fragment.addFragment(
    fragment: Fragment,
    containerViewId: Int,
    tag: String? = fragment.javaClass.name
) {
    FragmentUtils.addFragment(childFragmentManager, fragment, containerViewId, tag)
}


//隐藏Fragment
fun FragmentActivity.hideFragment(
    fragment: Fragment
) {
    FragmentUtils.hideFragment(supportFragmentManager, fragment)
}


//隐藏Fragment
fun Fragment.hideFragment(
    fragment: Fragment
) {
    FragmentUtils.hideFragment(childFragmentManager, fragment)
}

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