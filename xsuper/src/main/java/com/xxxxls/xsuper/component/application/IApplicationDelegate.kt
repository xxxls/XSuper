package com.xxxxls.xsuper.component.application

import androidx.annotation.Keep

/**
 * @author Max
 * @date 2019-12-01.
 */
@Keep
interface IApplicationDelegate {

    abstract fun onCreate()

    abstract fun onTerminate()

    abstract fun onLowMemory()

    abstract fun onTrimMemory(level: Int)
}