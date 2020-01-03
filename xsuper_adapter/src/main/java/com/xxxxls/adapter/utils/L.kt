package com.xxxxls.adapter.utils

import android.util.Log

/**
 * log日志
 * @author Max
 * @date 2019-11-26.
 */

internal object L {
    var TAG = "XSuper-Adapter"

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
