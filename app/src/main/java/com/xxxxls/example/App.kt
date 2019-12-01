package com.xxxxls.example

import com.alibaba.android.arouter.launcher.ARouter
import com.xxxxls.xsuper.component.application.XSuperApplication

/**
 * Application
 * @author Max
 * @date 2019-11-26.
 */
class App : XSuperApplication() {


    override fun onInitialize() {
        super.onInitialize()

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