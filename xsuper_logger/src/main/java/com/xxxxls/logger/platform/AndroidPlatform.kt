package com.xxxxls.logger.platform

import android.os.Build
import com.xxxxls.logger.printer.AndroidPrinter
import com.xxxxls.logger.printer.Printer

/**
 * android平台
 * @author Max
 * @date 2020-01-19.
 */
class AndroidPlatform : IPlatform {

    override fun getLineSeparator(): String {
        return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            "\n"
        } else System.lineSeparator()
    }

    override fun getDefaultPrinter(): Printer {
        return AndroidPrinter()
    }

    override fun warn(msg: String) {
        android.util.Log.w("XLog", msg)
    }

}