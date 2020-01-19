/*
 * Copyright 2015 Elvis Hew
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.xxxxls.logger.util


import com.xxxxls.logger.XSuperLogger

import java.io.PrintWriter
import java.io.StringWriter
import java.net.UnknownHostException

/**
 * Utility related with stack trace.
 */
object StackTraceUtil {

    private val XLOG_STACK_TRACE_ORIGIN: String

    init {
        // Let's start from xlog library.
        val xlogClassName = XSuperLogger::class.java.name
        XLOG_STACK_TRACE_ORIGIN = xlogClassName.substring(0, xlogClassName.lastIndexOf('.') + 1)
    }

    /**
     * Get a loggable stack trace from a Throwable
     *
     * @param tr An exception to log
     */
    fun getStackTraceString(tr: Throwable?): String {
        if (tr == null) {
            return ""
        }

        // This is to reduce the amount of log spew that apps do in the non-error
        // condition of the network being unavailable.
        var t = tr
        while (t != null) {
            if (t is UnknownHostException) {
                return ""
            }
            t = t.cause
        }

        val sw = StringWriter()
        val pw = PrintWriter(sw)
        tr.printStackTrace(pw)
        pw.flush()
        return sw.toString()
    }

    /**
     * Get the real stack trace and then crop it with a max depth.
     *
     * @param stackTrace the full stack trace
     * @param maxDepth   the max depth of real stack trace that will be cropped, 0 means no limitation
     * @return the cropped real stack trace
     */
    fun getCroppedRealStackTrack(
        stackTrace: Array<StackTraceElement>,
        stackTraceOrigin: String?,
        maxDepth: Int
    ): Array<StackTraceElement> {
        return cropStackTrace(getRealStackTrack(stackTrace, stackTraceOrigin), maxDepth)
    }

    /**
     * Get the real stack trace, all elements that come from XLog library would be dropped.
     *
     * @param stackTrace the full stack trace
     * @return the real stack trace, all elements come from system and library user
     */
    private fun getRealStackTrack(
        stackTrace: Array<StackTraceElement>,
        stackTraceOrigin: String?
    ): Array<StackTraceElement> {
        var ignoreDepth = 0
        val allDepth = stackTrace.size
        var className: String
        for (i in allDepth - 1 downTo 0) {
            className = stackTrace[i].className
            if (className.startsWith(XLOG_STACK_TRACE_ORIGIN) || stackTraceOrigin != null && className.startsWith(
                    stackTraceOrigin
                )
            ) {
                ignoreDepth = i + 1
                break
            }
        }
        val realDepth = allDepth - ignoreDepth
        val realStack = arrayOfNulls<StackTraceElement>(realDepth)
        System.arraycopy(stackTrace, ignoreDepth, realStack, 0, realDepth)
        return realStack.mapNotNull { it }.toTypedArray()
    }

    /**
     * Crop the stack trace with a max depth.
     *
     * @param callStack the original stack trace
     * @param maxDepth  the max depth of real stack trace that will be cropped,
     * 0 means no limitation
     * @return the cropped stack trace
     */
    private fun cropStackTrace(
        callStack: Array<StackTraceElement>,
        maxDepth: Int
    ): Array<StackTraceElement> {
        var realDepth = callStack.size
        if (maxDepth > 0) {
            realDepth = Math.min(maxDepth, realDepth)
        }
        val realStack = arrayOfNulls<StackTraceElement>(realDepth)
        System.arraycopy(callStack, 0, realStack, 0, realDepth)
        return realStack.mapNotNull { it }.toTypedArray()
    }
}
