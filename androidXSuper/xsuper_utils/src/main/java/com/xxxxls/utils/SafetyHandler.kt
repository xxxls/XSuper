package com.xxxxls.utils

import android.os.Handler
import android.os.Looper

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent

/**
 * 安全Handler
 * @author Max
 * @date 2020-01-10.
 */

class SafetyHandler : Handler, LifecycleObserver {

    constructor(activityOrFragment: LifecycleOwner) : super(Looper.getMainLooper()) {
        bindLifecycle(activityOrFragment)
    }

    constructor(
        activityOrFragment: LifecycleOwner,
        callback: Callback
    ) : super(Looper.getMainLooper(), callback) {
        bindLifecycle(activityOrFragment)
    }

    private fun bindLifecycle(activity: LifecycleOwner) {
        activity.lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        this.removeCallbacksAndMessages(null)
    }

}
