package com.xxxxls.xsuper.support

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.xxxxls.xsuper.util.Utils

/**
 * TOAST
 * @author Max
 * @date 2019-11-26.
 */

fun Context.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun Context.toast(@StringRes messageResId: Int, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, messageResId, duration).show()
}


fun Fragment.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    this.context?.apply {
        Toast.makeText(this, message, duration).show()
    }
}

fun Fragment.toast(@StringRes messageResId: Int, duration: Int = Toast.LENGTH_SHORT) {
    this.context?.apply {
        Toast.makeText(this, messageResId, duration).show()
    }
}

fun Any.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Utils.getApp()?.apply {
        Toast.makeText(this, message, duration).show()
    }
}

fun Any.toast(@StringRes messageResId: Int, duration: Int = Toast.LENGTH_SHORT) {
    Utils.getApp()?.apply {
        Toast.makeText(this, messageResId, duration).show()
    }
}
