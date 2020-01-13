package com.xxxxls.utils.ktx

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText


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
