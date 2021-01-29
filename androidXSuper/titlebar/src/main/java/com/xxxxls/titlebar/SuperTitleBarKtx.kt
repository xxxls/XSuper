package com.xxxxls.titlebar

import android.app.Activity
import android.view.View
import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

/**
 *
 * @author Max
 * @date 2020-01-07.
 */

//Activity - 获取标题栏
fun Activity?.getTitleBar(@IdRes id: Int = R.id.titlebar): SuperTitleBar? {
    return this?.findViewById<SuperTitleBar>(id)
}

//Fragment - 获取标题栏
fun Fragment?.getTitleBar(@IdRes id: Int = R.id.titlebar): SuperTitleBar? {
    return this?.view?.getTitleBar(id)
}

//View - 获取标题栏
fun View?.getTitleBar(@IdRes id: Int = R.id.titlebar): SuperTitleBar? {
    return this?.findViewById<SuperTitleBar>(id)
}

//Activity - 设置标题
fun Activity?.setTitleBarTitle(text: String?, @IdRes id: Int = R.id.titlebar): SuperTitleBar? {
    return this.getTitleBar(id)?.apply {
        this.setTitleText(text)
    }
}

//Fragment - 设置标题
fun Fragment?.setTitleBarTitle(text: String?, @IdRes id: Int = R.id.titlebar): SuperTitleBar? {
    return this.getTitleBar(id)?.apply {
        this.setTitleText(text)
    }
}

//View - 设置标题
fun View?.setTitleBarTitle(text: String?, @IdRes id: Int = R.id.titlebar): SuperTitleBar? {
    return this.getTitleBar(id)?.apply {
        this.setTitleText(text)
    }
}

//Activity - 设置标题
fun Activity?.setTitleBarTitle(@StringRes textIdRes: Int, @IdRes id: Int = R.id.titlebar): SuperTitleBar? {
    return this.getTitleBar(id)?.apply {
        this.setTitleText(textIdRes)
    }
}

//Fragment - 设置标题
fun Fragment?.setTitleBarTitle(@StringRes textIdRes: Int, @IdRes id: Int = R.id.titlebar): SuperTitleBar? {
    return this.getTitleBar(id)?.apply {
        this.setTitleText(textIdRes)
    }
}

//View - 设置标题
fun View?.setTitleBarTitle(@StringRes textIdRes: Int, @IdRes id: Int = R.id.titlebar): SuperTitleBar? {
    return this.getTitleBar(id)?.apply {
        this.setTitleText(textIdRes)
    }
}

//Activity - 设置副标题
fun Activity?.setTitleBarSubTitle(text: String?, @IdRes id: Int = R.id.titlebar): SuperTitleBar? {
    return this.getTitleBar(id)?.apply {
        this.setSubTitleText(text)
    }
}

//Fragment - 设置副标题
fun Fragment?.setTitleBarSubTitle(text: String?, @IdRes id: Int = R.id.titlebar): SuperTitleBar? {
    return this.getTitleBar(id)?.apply {
        this.setSubTitleText(text)
    }
}

//View - 设置副标题
fun View?.setTitleBarSubTitle(text: String?, @IdRes id: Int = R.id.titlebar): SuperTitleBar? {
    return this.getTitleBar(id)?.apply {
        this.setSubTitleText(text)
    }
}

//Activity - 设置副标题
fun Activity?.setTitleBarSubTitle(@StringRes textIdRes: Int, @IdRes id: Int = R.id.titlebar): SuperTitleBar? {
    return this.getTitleBar(id)?.apply {
        this.setSubTitleText(textIdRes)
    }
}

//Fragment - 设置副标题
fun Fragment?.setTitleBarSubTitle(@StringRes textIdRes: Int, @IdRes id: Int = R.id.titlebar): SuperTitleBar? {
    return this.getTitleBar(id)?.apply {
        this.setSubTitleText(textIdRes)
    }
}

//View - 设置副标题
fun View?.setTitleBarSubTitle(@StringRes textIdRes: Int, @IdRes id: Int = R.id.titlebar): SuperTitleBar? {
    return this.getTitleBar(id)?.apply {
        this.setSubTitleText(textIdRes)
    }
}


//Activity - 设置左图标
fun Activity?.setTitleBarLeftIcon(
    @DrawableRes iconResIdRes: Int, @IdRes id: Int = R.id.titlebar, onClickListener: ((view: View) -> Unit)? = null
): SuperTitleBar? {
    return this.getTitleBar(id)?.apply {
        this.setLeftIcon(iconResIdRes)
        setOnLeftTitleClickListener(onClickListener)
    }
}

//Fragment - 设置左图标
fun Fragment?.setTitleBarLeftIcon(
    @DrawableRes iconResIdRes: Int, @IdRes id: Int = R.id.titlebar, onClickListener: ((view: View) -> Unit)? = null
): SuperTitleBar? {
    return this.getTitleBar(id)?.apply {
        this.setLeftIcon(iconResIdRes)
        setOnLeftTitleClickListener(onClickListener)
    }
}

//View - 设置左图标
fun View?.setTitleBarLeftIcon(
    @DrawableRes iconResIdRes: Int, @IdRes id: Int = R.id.titlebar, onClickListener: ((view: View) -> Unit)? = null
): SuperTitleBar? {
    return this.getTitleBar(id)?.apply {
        this.setLeftIcon(iconResIdRes)
        setOnLeftTitleClickListener(onClickListener)
    }
}


//Activity - 设置左文本
fun Activity?.setTitleBarLeftText(
    @StringRes textIdRes: Int, @IdRes id: Int = R.id.titlebar, onClickListener: ((view: View) -> Unit)? = null
): SuperTitleBar? {
    return this.getTitleBar(id)?.apply {
        this.setLeftText(textIdRes)
        setOnLeftTitleClickListener(onClickListener)
    }
}

//Fragment - 设置左文本
fun Fragment?.setTitleBarLeftText(
    @StringRes textIdRes: Int, @IdRes id: Int = R.id.titlebar, onClickListener: ((view: View) -> Unit)? = null
): SuperTitleBar? {
    return this.getTitleBar(id)?.apply {
        this.setLeftText(textIdRes)
        setOnLeftTitleClickListener(onClickListener)
    }
}

//View - 设置左文本
fun View?.setTitleBarLeftText(
    @StringRes textIdRes: Int, @IdRes id: Int = R.id.titlebar, onClickListener: ((view: View) -> Unit)? = null
): SuperTitleBar? {
    return this.getTitleBar(id)?.apply {
        this.setLeftText(textIdRes)
        setOnLeftTitleClickListener(onClickListener)
    }
}


//Activity - 设置左文本
fun Activity?.setTitleBarLeftText(
    text: String?, @IdRes id: Int = R.id.titlebar,
    onClickListener: ((view: View) -> Unit)? = null
): SuperTitleBar? {
    return this.getTitleBar(id)?.apply {
        this.setLeftText(text)
        setOnLeftTitleClickListener(onClickListener)
    }
}

//Fragment - 设置左文本
fun Fragment?.setTitleBarLeftText(
    text: String?, @IdRes id: Int = R.id.titlebar,
    onClickListener: ((view: View) -> Unit)? = null
): SuperTitleBar? {
    return this.getTitleBar(id)?.apply {
        this.setLeftText(text)
        setOnLeftTitleClickListener(onClickListener)
    }
}

//View - 设置左文本
fun View?.setTitleBarLeftText(
    text: String?,
    @IdRes id: Int = R.id.titlebar,
    onClickListener: ((view: View) -> Unit)? = null
): SuperTitleBar? {
    return this.getTitleBar(id)?.apply {
        this.setLeftText(text)
        setOnLeftTitleClickListener(onClickListener)
    }
}


//Activity - 设置右图标
fun Activity?.setTitleBarRightIcon(
    @DrawableRes iconResIdRes: Int, @IdRes id: Int = R.id.titlebar, onClickListener: ((view: View) -> Unit)? = null
): SuperTitleBar? {
    return this.getTitleBar(id)?.apply {
        this.setRightIcon(iconResIdRes)
        setOnRightTitleClickListener(onClickListener)
    }
}

//Fragment - 设置右图标
fun Fragment?.setTitleBarRightIcon(
    @DrawableRes iconResIdRes: Int, @IdRes id: Int = R.id.titlebar, onClickListener: ((view: View) -> Unit)? = null
): SuperTitleBar? {
    return this.getTitleBar(id)?.apply {
        this.setRightIcon(iconResIdRes)
        setOnRightTitleClickListener(onClickListener)
    }
}

//View - 设置右图标
fun View?.setTitleBarRightIcon(
    @DrawableRes iconResIdRes: Int, @IdRes id: Int = R.id.titlebar, onClickListener: ((view: View) -> Unit)? = null
): SuperTitleBar? {
    return this.getTitleBar(id)?.apply {
        this.setRightIcon(iconResIdRes)
        setOnRightTitleClickListener(onClickListener)
    }
}


//Activity - 设置右文本
fun Activity?.setTitleBarRightText(
    @StringRes textIdRes: Int, @IdRes id: Int = R.id.titlebar, onClickListener: ((view: View) -> Unit)? = null
): SuperTitleBar? {
    return this.getTitleBar(id)?.apply {
        this.setRightText(textIdRes)
        setOnRightTitleClickListener(onClickListener)
    }
}

//Fragment - 设置右文本
fun Fragment?.setTitleBarRightText(
    @StringRes textIdRes: Int, @IdRes id: Int = R.id.titlebar, onClickListener: ((view: View) -> Unit)? = null
): SuperTitleBar? {
    return this.getTitleBar(id)?.apply {
        this.setRightText(textIdRes)
        setOnRightTitleClickListener(onClickListener)
    }
}

//View - 设置右文本
fun View?.setTitleBarRightText(
    @StringRes textIdRes: Int, @IdRes id: Int = R.id.titlebar, onClickListener: ((view: View) -> Unit)? = null
): SuperTitleBar? {
    return this.getTitleBar(id)?.apply {
        this.setRightText(textIdRes)
        setOnRightTitleClickListener(onClickListener)
    }
}


//Activity - 设置右文本
fun Activity?.setTitleBarRightText(
    text: String?, @IdRes id: Int = R.id.titlebar,
    onClickListener: ((view: View) -> Unit)? = null
): SuperTitleBar? {
    return this.getTitleBar(id)?.apply {
        this.setRightText(text)
        setOnRightTitleClickListener(onClickListener)
    }
}

//Fragment - 设置右文本
fun Fragment?.setTitleBarRightText(
    text: String?, @IdRes id: Int = R.id.titlebar,
    onClickListener: ((view: View) -> Unit)? = null
): SuperTitleBar? {
    return this.getTitleBar(id)?.apply {
        this.setRightText(text)
        setOnRightTitleClickListener(onClickListener)
    }
}

//View - 设置右文本
fun View?.setTitleBarRightText(
    text: String?, @IdRes id: Int = R.id.titlebar,
    onClickListener: ((view: View) -> Unit)? = null
): SuperTitleBar? {
    return this.getTitleBar(id)?.apply {
        this.setRightText(text)
        setOnRightTitleClickListener(onClickListener)
    }
}

//XSuperTitleBar - 设置左点击事件
fun SuperTitleBar?.setOnLeftTitleClickListener(onClickListener: ((view: View) -> Unit)? = null): SuperTitleBar? {
    return this?.apply {
        onClickListener?.let {
            this.onLeftTitleClickListener = object : OnLeftTitleClickListener {
                override fun onLeftTitleClick(view: View) {
                    it(view)
                }
            }
        }
    }
}


//XSuperTitleBar - 设置右点击事件
fun SuperTitleBar?.setOnRightTitleClickListener(onClickListener: ((view: View) -> Unit)? = null): SuperTitleBar? {
    return this?.apply {
        onClickListener?.let {
            this.onRightTitleClickListener = object : OnRightTitleClickListener {
                override fun onRightTitleClick(view: View) {
                    it(view)
                }
            }
        }
    }
}

