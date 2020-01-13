package com.xxxxls.utils

import android.os.CountDownTimer
import androidx.lifecycle.LifecycleObserver

/**
 *
 * @author Max
 * @date 2020-01-13.
 */
abstract class LifecycleCountDownTimer(millisInFuture: Long, countDownInterval: Long) :
    CountDownTimer(millisInFuture, countDownInterval), LifecycleObserver {

}