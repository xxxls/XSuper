package com.xxxxls.utils.ktx

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.xxxxls.utils.AppUtils

/**
 * TOAST
 * @author Max
 * @date 2019-11-26.
 */

fun Context.toast(message: CharSequence?, duration: Int = Toast.LENGTH_SHORT) {
    message?.let {
        Toast.makeText(this, message, duration).show()
    }
}

fun Context.toast(@StringRes messageResId: Int?, duration: Int = Toast.LENGTH_SHORT) {
    messageResId?.let {
        Toast.makeText(this, messageResId, duration).show()
    }
}


fun Fragment.toast(message: CharSequence?, duration: Int = Toast.LENGTH_SHORT) {
    this.context?.apply {
        Toast.makeText(this, message, duration).show()
    }
}

fun Fragment.toast(@StringRes messageResId: Int?, duration: Int = Toast.LENGTH_SHORT) {
    this.context?.apply {
        messageResId?.let {
            Toast.makeText(this, it, duration).show()
        }
    }
}

fun Any.toast(message: CharSequence?, duration: Int = Toast.LENGTH_SHORT) {
    AppUtils.getApp()?.apply {
        message?.let {
            Toast.makeText(this, message, duration).show()
        }
    }
}

fun Any.toast(@StringRes messageResId: Int?, duration: Int = Toast.LENGTH_SHORT) {
    AppUtils.getApp()?.apply {
        messageResId?.let {
            Toast.makeText(this, it, duration).show()
        }
    }
}
