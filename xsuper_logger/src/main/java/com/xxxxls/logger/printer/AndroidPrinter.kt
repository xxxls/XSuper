package com.xxxxls.logger.printer

import android.util.Log
import com.xxxxls.logger.LogLevel

/**
 * Android打印
 * @author Max
 * @date 2020-01-19.
 */
class AndroidPrinter : IPrinter {

    override fun println(logLevel: LogLevel, tag: String, msg: String) {
        Log.println(logLevel.level,tag, msg)
    }

}