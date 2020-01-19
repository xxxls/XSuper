package com.xxxxls.logger.platform

import android.os.Build

/**
 * 平台
 * @author Max
 * @date 2020-01-19.
 */

object Platform {

    //平台
    private val PLATFORM: IPlatform by lazy {
        findPlatform()
    }

    /**
     * 查找本平台
     */
    private fun findPlatform(): IPlatform {
        try {
            Class.forName("android.os.Build")
            if (Build.VERSION.SDK_INT != 0) {
                return AndroidPlatform()
            }
        } catch (ignored: ClassNotFoundException) {
        }

        return AndroidPlatform()
    }

    fun get(): IPlatform {
        return PLATFORM
    }

}