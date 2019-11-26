package com.xxxxls.example

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter

/**
 * Application
 * @author Max
 * @date 2019-11-26.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            ARouter.openLog()
            ARouter.openDebug()
        }
        ARouter.init(this)
    }

    override fun onTerminate() {
        super.onTerminate()
        ARouter.getInstance().destroy()
    }
}