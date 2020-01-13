package com.xxxxls.utils.ktx

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.xxxxls.utils.FragmentUtils

/**
 * Fragment扩展
 * @author Max
 * @date 2020-01-10.
 */

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
