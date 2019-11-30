package com.xxxxls.xsuper.util

import android.util.Log

/**
 * log日志
 * @author Max
 * @date 2019-11-26.
 */

object L {
    var TAG = "XSuper"

    fun e(tag: String = TAG, message: String?) {
        Log.e(tag, message ?: "")
    }

    fun d(tag: String = TAG, message: String?) {
        Log.d(tag, message ?: "")
    }

    fun e(message: String?) {
        Log.e(TAG, message ?: "")
    }

    fun d(message: String?) {
        Log.d(TAG, message ?: "")
    }
}


fun String?.logE(tag: String = L.TAG) {
    L.e(tag = tag, message = this)
}

fun String?.logD(tag: String = L.TAG) {
    L.e(tag = tag, message = this)
}
