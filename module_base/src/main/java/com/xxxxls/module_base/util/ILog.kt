package com.xxxxls.module_base.util

import android.util.ArrayMap
import com.xxxxls.logger.BuildConfig
import com.xxxxls.logger.XLogger
import com.xxxxls.logger.XLoggerLevel

/**
 * log 日志扩展（方便灵活的控制某个实例下日志功能）
 * @author Max
 * @date 2020/7/7.
 */
interface ILog {
    companion object {

        // 日志功能开关
        private var enabled: Boolean? = null

        // 日志的TAG追加调用者#HashCode
        private var tagHashCodeEnabled: Boolean = false

        // TAG过滤标签
        private var filterTags: ArrayMap<String, Boolean>? = null

        /**
         * 设置日志功能是否启动（全局）
         */
        fun setEnabled(isEnabled: Boolean) {
            enabled = isEnabled
        }

        /**
         * 是否启动日志功能（全局的开关，优先度>单个实例的开关）
         * 默认根据当前运行环境决定是否启用日志
         */
        fun isEnabled(): Boolean {
            return enabled
                ?: BuildConfig.DEBUG
        }

        /**
         * 设置是否启用 日志的TAG追加调用者#HashCode
         * //TODO HashCode还未实现
         */
        private fun setTagHashCodeEnabled(isEnabled: Boolean) {
            tagHashCodeEnabled = isEnabled
        }

        /**
         * 添加过滤标签
         * @param tag 该标签将不会显示
         * @param isExactMatch 是否完全匹配
         */
        fun addFilterTag(tag: String, isExactMatch: Boolean = true) {
            if (filterTags == null) {
                filterTags = ArrayMap()
            }
            filterTags?.put(tag, isExactMatch)
        }

        /**
         * 移除过滤标签
         * @param tag 移除该标签的显示
         */
        fun removeFilterTag(tag: String) {
            filterTags?.remove(tag)
        }

        /**
         * 是否过滤的TAG
         * @param tag 日志TAG
         * @return 是否过滤此TAG的日志
         */
        fun isFilterTag(tag: String): Boolean {
            filterTags?.forEach {
                val isFilter = if (it.value) {
                    it.key == tag
                } else {
                    tag.contains(it.key)
                }
                if (isFilter) {
                    return true
                }
            }
            return false
        }
    }

    /**
     * 设置是否启动日志（实现类可重写）
     * (只针对当前实例下的日志功能)
     */
    fun isLogEnabled(): Boolean {
        return isEnabled()
    }

    /**
     * 本次日志是否可以打印
     */
    private fun isLog(tag: String): Boolean {
        // 全局的配置 > 单个实例的配置
        if (!isEnabled()) {
            return false
        }

        // 该TAG属于被过滤的
        if (isFilterTag(tag)) {
            return false
        }

        return isLogEnabled()
    }

    /**
     * 默认日志Tag
     */
    fun getLogTag(): String {
        return if (tagHashCodeEnabled) {
            this::class.java.simpleName + "#" + this::class.java.hashCode()
        } else {
            this::class.java.simpleName
        }
    }

    /**
     * 默认日志级别
     */
    fun getLogDefaultLevel(): Int {
        return XLoggerLevel.INFO
    }

    /**
     * 打印
     * @param message 消息
     * @param tag
     * @param level
     */
    fun log(
        message: CharSequence,
        tag: String = getLogTag(),
        level: Int = getLogDefaultLevel()
    ) {
        if (!isLog(tag)) {
            return
        }
        XLogger.log(message, tag, level)
    }

    fun logE(message: CharSequence, tag: String = getLogTag()) {
        if (!isLog(tag)) {
            return
        }
        XLogger.e(tag, message.toString())
    }

    fun logD(message: CharSequence, tag: String = getLogTag()) {
        if (!isLog(tag)) {
            return
        }
        XLogger.d(tag, message.toString())
    }

    fun logW(message: CharSequence, tag: String = getLogTag()) {
        if (!isLog(tag)) {
            return
        }
        XLogger.w(tag, message.toString())
    }

    fun logI(message: CharSequence, tag: String = getLogTag()) {
        if (!isLog(tag)) {
            return
        }
        XLogger.i(tag, message.toString())
    }

    fun logV(message: CharSequence, tag: String = getLogTag()) {
        if (!isLog(tag)) {
            return
        }
        XLogger.v(tag, message.toString())
    }
}