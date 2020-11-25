package com.xxxxls.example

import androidx.multidex.MultiDex
import com.alibaba.android.arouter.launcher.ARouter
import com.xxxxls.xsuper.component.application.XSuperApplication
import dagger.hilt.android.HiltAndroidApp

/**
 * Application
 * @author Max
 * @date 2019-11-26.
 */
@HiltAndroidApp
class App : XSuperApplication() {

    override fun onInitialize() {
        super.onInitialize()
        MultiDex.install(this)
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