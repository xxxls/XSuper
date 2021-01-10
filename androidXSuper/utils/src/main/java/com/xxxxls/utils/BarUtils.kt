package com.xxxxls.utils

import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.annotation.NonNull

/**
 * 系统各种栏
 * @author Max
 * @date 2019-12-14.
 */


/**
 * 获取状态栏高度——方法
 */
fun getStatusBarHeight(): Int {
    var statusBarHeight = -1
    //获取status_bar_height资源的ID
    val resourceId = AppUtils.getApp().resources
        .getIdentifier("status_bar_height", "dimen", "android")
    if (resourceId > 0) {
        //根据资源ID获取响应的尺寸值
        statusBarHeight = AppUtils.getApp().resources.getDimensionPixelSize(resourceId)
    }
    return statusBarHeight
}


/**
 * 透明状态栏
 *
 * @param activity
 */
fun translucentStatusBar(activity: Activity) {
    val win = activity.window
    //KITKAT也能满足，只是SYSTEM_UI_FLAG_LIGHT_STATUS_BAR（状态栏字体颜色反转）只有在6.0才有效
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        //透明状态栏
        win.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        // 状态栏字体设置为深色，SYSTEM_UI_FLAG_LIGHT_STATUS_BAR 为SDK23增加
        /* win.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN |
                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);*/
        // 不调节状态栏文字颜色
        win.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        // 部分机型的statusBar会有半透明的黑色背景
        win.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        win.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        // SDK21
        win.statusBarColor = Color.TRANSPARENT
    }
}

/**
 * 设置状态栏文字为深颜色
 */
fun setLightTranslucentStatusBar(activity: Activity) {
    val win = activity.window
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        win.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //将字体颜色设置为深色
            win.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
        win.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        win.statusBarColor = Color.TRANSPARENT
    } else {
        win.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    }
}

fun setLightStatusBar(activity: Activity) {
    val win = activity.window
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        win.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        win.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        win.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            win.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
        win.statusBarColor = Color.WHITE
    }
}

/**
 * 设置状态栏文字为浅颜色
 */
fun setDarkTranslucentStatusBar(activity: Activity) {
    val win = activity.window
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        win.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        win.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        win.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        win.statusBarColor = Color.TRANSPARENT
    } else {
        win.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    }
}

fun setDarkStatusBar(activity: Activity) {
    val win = activity.window
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        win.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        win.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        win.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
        win.statusBarColor = Color.BLACK
    }
}


/**
 * 判断全面屏是否启用虚拟导航栏
 */
fun isNavigationBarExist(@NonNull activity: Activity): Boolean {
    val viewGroup = activity.window.decorView as ViewGroup
    for (i in 0 until viewGroup.childCount) {
        viewGroup.getChildAt(i).context.packageName
        if (viewGroup.getChildAt(i).id !== -1 && "navigationBarBackground" == activity.getResources().getResourceEntryName(
                viewGroup.getChildAt(i).id
            )
        ) {
            return true
        }
    }
    return false
}
