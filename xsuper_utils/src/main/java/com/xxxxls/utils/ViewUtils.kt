package com.xxxxls.utils

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Checkable
import android.widget.EditText

/**
 *
 * @author Max
 * @date 2019-11-29.
 */


/**
 * 展示or隐藏
 */
inline fun View.visibleOrGone(show: View.() -> Boolean = { true }) {
    visibility = if (show(this)) {
        View.VISIBLE
    } else {
        View.GONE
    }
}

/**
 * 展示or不可见
 */
inline fun View.visibleOrInvisible(show: View.() -> Boolean = { true }) {
    visibility = if (show(this)) {
        View.VISIBLE
    } else {
        View.INVISIBLE
    }
}

/**
 * 单点
 */
inline fun <T : View> T.singleClick(time: Long = 800, crossinline block: (T) -> Unit) {
    setOnClickListener {
        val currentTimeMillis = System.currentTimeMillis()
        if (currentTimeMillis - lastClickTime > time || this is Checkable) {
            lastClickTime = currentTimeMillis
            block(this)
        }
    }
}

//兼容点击事件设置为this的情况
fun <T : View> T.singleClick(onClickListener: View.OnClickListener, time: Long = 800) {
    setOnClickListener {
        val currentTimeMillis = System.currentTimeMillis()
        if (currentTimeMillis - lastClickTime > time || this is Checkable) {
            lastClickTime = currentTimeMillis
            onClickListener.onClick(this)
        }
    }
}

var <T : View> T.lastClickTime: Long
    set(value) = setTag(1766613352, value)
    get() = getTag(1766613352) as? Long ?: 0


/**
 * 设置editText输入监听
 * @param onChanged 改变事件
 */
inline fun EditText.setOnInputChangedListener(
    /**
     * @param Int：当前长度
     * @return 是否接受此次文本的改变
     */
    crossinline onChanged: (Int).() -> Boolean
) {
    this.addTextChangedListener(object : TextWatcher {

        var flag = false

        override fun afterTextChanged(p0: Editable?) {
            if (flag) {
                return
            }
            if (!onChanged(p0?.length ?: 0)) {
                flag = true
                this@setOnInputChangedListener.setText(
                    this@setOnInputChangedListener.getTag(
                        1982329101
                    ) as? String
                )
                this@setOnInputChangedListener.setSelection(this@setOnInputChangedListener.length())
                flag = false
            } else {
                flag = false
            }
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            this@setOnInputChangedListener.setTag(1982329101, p0?.toString())
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }
    })
}
