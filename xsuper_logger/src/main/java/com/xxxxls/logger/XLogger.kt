package com.xxxxls.logger

import android.util.Log
import com.elvishew.xlog.LogConfiguration
import com.elvishew.xlog.LogLevel
import com.elvishew.xlog.XLog
import com.elvishew.xlog.interceptor.BlacklistTagsFilterInterceptor
import com.elvishew.xlog.interceptor.WhitelistTagsFilterInterceptor
import com.elvishew.xlog.printer.AndroidPrinter
import com.elvishew.xlog.printer.Printer
import com.elvishew.xlog.printer.file.FilePrinter
import com.elvishew.xlog.printer.file.naming.DateFileNameGenerator
import java.io.File

/**
 * LOGGER
 * @author Max
 * @date 2020-01-18.
 */
object XLogger {

    /**
     * 是否已经初始化了
     */
    private var isInitialized = false

    /**
     * 初始化
     * @param config 配置
     */
    fun init(config: LoggerConfig = createDefaultConfig()) {
        this.isInitialized = true
        val printers: ArrayList<Printer> = ArrayList()
        printers.add(AndroidPrinter())                       // 通过 android.util.Log 打印日志的打印器
//        printers.add(ConsolePrinter())                       // 通过 System.out 打印日志到控制台的打印器
        config.filePath?.let {
            val filePrinter = FilePrinter         // 打印日志到文件的打印器
                .Builder(it.absolutePath)                    // 指定保存日志文件的路径
                .fileNameGenerator(DateFileNameGenerator())  // 指定日志文件名生成器，默认为 ChangelessFileNameGenerator("log")
//            .backupStrategy(
//                 NeverBackupStrategy ()                    // 指定日志文件备份策略，默认为 FileSizeBackupStrategy(1024 * 1024)
//                    .cleanStrategy( FileLastModifiedCleanStrategy (MAX_TIME))     // 指定日志文件清除策略，默认为 NeverCleanStrategy()
//                    .flattener( MyFlattener ())            // 指定日志平铺器，默认为 DefaultFlattener
                .build()
            printers.add(filePrinter)
        }
        XLog.init(                                           // 初始化 XLog
            conversionConfig(config),                        // 指定日志配置，如果不指定，会默认使用 new LogConfiguration.Builder().build()
            * printers.toTypedArray()
        )
    }

    /**
     * log-系统打印
     */
    fun logS(message: String, tag: String? = null, level: Int = LogLevel.DEBUG) {
        Log.println(XLoggerLevel.toSystemLogLevel(level), tag ?: "", message)
    }

    /**
     * 检查初始化
     */
    private fun checkInitialized() {
        if (!isInitialized) {
            // 默认初始化
            init()
        }
    }

    /**
     * log
     */
    fun log(message: String, tag: String? = null, level: Int = LogLevel.DEBUG) {
        checkInitialized()
        if (tag != null) {
            XLog.log(level, message)
        } else {
            XLog.tag(tag).log(level, message)
        }
    }

    /**
     * log
     */
    fun log(message: Any, tag: String? = null, level: Int = LogLevel.DEBUG) {
        checkInitialized()
        if (tag != null) {
            XLog.log(level, message)
        } else {
            XLog.tag(tag).log(level, message)
        }
    }

    /**
     * log
     */
    fun log(message: Array<Any>, tag: String? = null, level: Int = LogLevel.DEBUG) {
        checkInitialized()
        if (tag != null) {
            XLog.log(level, message)
        } else {
            XLog.tag(tag).log(level, message)
        }
    }

    /**
     * log-D
     */
    fun d(message: String, tag: String? = null) {
        checkInitialized()
        if (tag != null) {
            XLog.d(message)
        } else {
            XLog.tag(tag).d(message)
        }
    }

    /**
     * log-E
     */
    fun e(message: String, tag: String? = null) {
        checkInitialized()
        if (tag != null) {
            XLog.e(message)
        } else {
            XLog.tag(tag).e(message)
        }
    }

    /**
     * log-W
     */
    fun w(message: String, tag: String? = null) {
        checkInitialized()
        if (tag != null) {
            XLog.w(message)
        } else {
            XLog.tag(tag).w(message)
        }
    }

    /**
     * log-I
     */
    fun i(message: String, tag: String? = null) {
        checkInitialized()
        if (tag != null) {
            XLog.i(message)
        } else {
            XLog.tag(tag).i(message)
        }
    }

    /**
     * log-V
     */
    fun v(message: String, tag: String? = null) {
        checkInitialized()
        if (tag != null) {
            XLog.v(message)
        } else {
            XLog.tag(tag).v(message)
        }
    }

    /**
     * log-Json
     */
    fun json(message: String, tag: String? = null) {
        checkInitialized()
        if (tag != null) {
            XLog.json(message)
        } else {
            XLog.tag(tag).json(message)
        }
    }

    /**
     * log-xml
     */
    fun xml(message: String, tag: String? = null) {
        checkInitialized()
        if (tag != null) {
            XLog.xml(message)
        } else {
            XLog.tag(tag).xml(message)
        }
    }


    /**
     * 创建默认配置
     */
    fun createDefaultConfig(): LoggerConfig {
        return LoggerConfig().apply {
            this.logLevel = if (BuildConfig.DEBUG) LogLevel.ALL else LogLevel.NONE
            this.borderEnabled = true
            this.threadEnabled = true
            this.tag = BuildConfig.LIBRARY_PACKAGE_NAME
            this.depth = 2
        }
    }

    /**
     * 转换
     */
    private fun conversionConfig(config: LoggerConfig): LogConfiguration {
        return LogConfiguration.Builder().apply {
            // 指定日志级别，低于该级别的日志将不会被打印，默认为 LogLevel.ALL
            this.logLevel(config.logLevel)

            // 指定 TAG，默认为 "X-LOG"
            this.tag(config.tag)

            // 允许打印深度为2的调用栈信息，默认禁止
            this.st(config.depth)

            if (config.threadEnabled) {
                // 允许打印线程信息，默认禁止
                this.t()
            } else {
                this.nt()
            }

            if (config.borderEnabled) {
                // 允许打印日志边框，默认禁止
                this.b()
            } else {
                this.nb()
            }

            // 添加黑名单 TAG 过滤器
            config.blackListTagList?.let {
                if (it.isNotEmpty()) {
                    this.addInterceptor(BlacklistTagsFilterInterceptor(it))
                }
            }

            // 添加白名单 TAG 过滤器
            config.whiteListTagList?.let {
                if (it.isNotEmpty()) {
                    this.addInterceptor(WhitelistTagsFilterInterceptor(it))
                }
            }

//            this.jsonFormatter( MyJsonFormatter())                  // 指定 JSON 格式化器，默认为 DefaultJsonFormatter
//            this.xmlFormatter( MyXmlFormatter())                    // 指定 XML 格式化器，默认为 DefaultXmlFormatter
//            this.throwableFormatter( MyThrowableFormatter ())        // 指定可抛出异常格式化器，默认为 DefaultThrowableFormatter
//            this.threadFormatter( MyThreadFormatter ())              // 指定线程信息格式化器，默认为 DefaultThreadFormatter
//            this.stackTraceFormatter( MyStackTraceFormatter ())      // 指定调用栈信息格式化器，默认为 DefaultStackTraceFormatter
//            this.borderFormatter( MyBoardFormatter ())               // 指定边框格式化器，默认为 DefaultBorderFormatter
//            this.addObjectFormatter(
//                AnyClass.class,                    // 为指定类添加格式化器
//                        new AnyClassObjectFormatter()
//            )                     // 默认使用 Object.toString()
//            this.addInterceptor( MyInterceptor ())                   // 添加一个日志拦截器
        }.build()
    }

    class LoggerConfig {
        // 日志级别
        var logLevel: Int = LogLevel.ALL

        // 默认日志标签
        var tag: String = "TAG"

        // 启动边框？
        var borderEnabled: Boolean = true

        //调用栈深度
        var depth: Int = 2

        // 打印线程信息？
        var threadEnabled: Boolean = true

        // 文件记录的存储路径
        var filePath: File? = null

        // 黑名单TAG
        var blackListTagList: ArrayList<String>? = null

        // 白名单TAG
        var whiteListTagList: ArrayList<String>? = null
    }
}