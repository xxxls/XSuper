package com.xxxxls.xsuper.util

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.util.DisplayMetrics
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import androidx.annotation.NonNull

/**
 *
 * @author Max
 * @date 2019-12-10.
 */

fun Long.dp(): Int {
    return UiUtils.px2dip(this.toFloat())
}


fun Float.dp(): Int {
    return UiUtils.px2dip(this)
}


fun Int.dp(): Int {
    return UiUtils.px2dip(this.toFloat())
}


fun Long.px(): Int {
    return UiUtils.dip2px(this.toFloat())
}


fun Float.px(): Int {
    return UiUtils.dip2px(this)
}


fun Int.px(): Int {
    return UiUtils.dip2px(this.toFloat())
}

object UiUtils {
    fun getScreenWidth(context: Context): Int {
        val localDisplayMetrics = DisplayMetrics()
        (context as Activity).windowManager.defaultDisplay.getMetrics(localDisplayMetrics)
        return localDisplayMetrics.widthPixels
    }

    fun getScreenHeight(context: Context): Int {
        val localDisplayMetrics = DisplayMetrics()
        (context as Activity).windowManager.defaultDisplay.getMetrics(localDisplayMetrics)
        return localDisplayMetrics.heightPixels
    }

    fun getScreenRatio(context: Context): Float {
        return getScreenWidth(context) * 1.0f / getScreenHeight(context)
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    fun dip2px(dpValue: Float): Int {
        val scale = Utils.getApp().resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    fun px2dip(pxValue: Float): Int {
        val scale = Utils.getApp().resources.displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }

    /**
     * 获取状态栏高度——方法
     */
    fun getStatusBarHeight(): Int {
        var statusBarHeight = -1
        //获取status_bar_height资源的ID
        val resourceId = Utils.getApp().resources
            .getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusBarHeight = Utils.getApp().resources.getDimensionPixelSize(resourceId)
        }
        return statusBarHeight
    }

    /**
     * 弹出输入法
     *
     * @param v 需要输入的view
     */
    fun showSoftInput(v: View) {
        val context = v.context
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        var activity: Activity? = null
        if (context is Activity) {
            activity = context
        }
        if (inputMethodManager != null) {
            v.isFocusable = true
            v.isFocusableInTouchMode = true
            v.requestFocus()
            inputMethodManager.showSoftInput(v, InputMethodManager.SHOW_FORCED)
            if (activity != null && !activity.isFinishing) {
                activity.window.setSoftInputMode(
                    WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE
                )
            }
        }
    }

    /**
     * 收起输入法
     *
     * @param v 当前页面的view
     */
    fun hideSoftInput(v: View) {
        val context = v.context
        val inputMethodManager =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (inputMethodManager != null && inputMethodManager.isActive) {
            inputMethodManager.hideSoftInputFromWindow(v.windowToken, 0)
        }
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
}
