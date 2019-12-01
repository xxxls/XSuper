package com.xxxxls.xsuper.component.application

import android.app.Application
import android.text.TextUtils
import androidx.annotation.CallSuper
import com.xxxxls.xsuper.util.ClassInterfaceUtils
import java.io.BufferedReader
import java.io.FileReader
import java.io.IOException

/**
 * super - Application
 * @author Max
 * @date 2019-12-01.
 */
open class XSuperApplication : Application() {


    private val ROOT_PACKAGE = "com.xxxxls"

    private var mAppDelegateList: List<IApplicationDelegate>? = null

    override fun onCreate() {
        super.onCreate()
        initApplicationDelegate()
        if (packageName == getProcessName(android.os.Process.myPid())) {
            onInitialize()
        }
    }

    override fun onTerminate() {
        super.onTerminate()
    }

    override fun onLowMemory() {
        super.onLowMemory()
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
    }

    @CallSuper
    protected open fun onInitialize() {
    }

    /**
     * 初始化 Application
     */
    private fun initApplicationDelegate(){
        mAppDelegateList =
            ClassInterfaceUtils.getObjectsWithInterface(
                this,
                IApplicationDelegate::class.java,
                ROOT_PACKAGE
            )
        mAppDelegateList?.forEach {
            it.onCreate()
        }
    }


    /**
     * 获取进程号对应的进程名
     *
     * @param pid 进程号
     * @return 进程名
     */
    private fun getProcessName(pid: Int): String? {
        var reader: BufferedReader? = null
        try {
            reader = BufferedReader(FileReader("/proc/$pid/cmdline"))
            var processName = reader.readLine()
            if (!TextUtils.isEmpty(processName)) {
                processName = processName.trim { it <= ' ' }
            }
            return processName
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
        } finally {
            try {
                reader?.close()
            } catch (exception: IOException) {
                exception.printStackTrace()
            }

        }
        return null
    }

}