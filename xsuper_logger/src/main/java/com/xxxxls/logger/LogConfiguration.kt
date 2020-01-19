package com.xxxxls.logger

import com.xxxxls.logger.interceptor.LogInterceptor


/**
 * 日志配置
 * @author Max
 * @date 2020-01-19.
 */
class LogConfiguration
private constructor(builder: Builder) {

    /**
     * 全局默认标签 PS：当打印的日志没有声明标签时，使用此默认标签
     */
    val tag: String

    /**
     * 是否展示日志边框
     */
    val withBorder: Boolean

    /**
     * 是否展示日志线程信息
     */
    val withThread: Boolean

    /**
     * 是否展示堆栈跟踪信息
     */
    val withStackTrace: Boolean

    /**
     * 堆栈跟踪信息深度
     */
    val stackTraceDepth: Int

    /**
     * 日志拦截器
     */
    val interceptors: MutableList<LogInterceptor>?

    init {
        this.tag = builder.tag
        this.withBorder = builder.withBorder
        this.withThread = builder.withThread
        this.withStackTrace = builder.withStackTrace
        this.stackTraceDepth = builder.stackTraceDepth
        this.interceptors = builder.interceptors
    }

    companion object {

        /**
         * 构建实例
         */
        fun newBuilder(): Builder {
            return Builder()
        }

        class Builder {
            /**
             * 全局默认标签 PS：当打印的日志没有声明标签时，使用此默认标签
             */
            internal var tag: String = "XSUPER-LOGGER"

            /**
             * 是否展示日志边框
             */
            internal var withBorder: Boolean = true

            /**
             * 是否展示日志线程信息
             */
            internal var withThread: Boolean = false

            /**
             * 是否展示堆栈跟踪信息
             */
            internal var withStackTrace: Boolean = false

            /**
             * 堆栈跟踪信息深度
             */
            internal var stackTraceDepth: Int = 2

            /**
             * 拦截器
             */
            internal val interceptors: MutableList<LogInterceptor> by lazy {
                ArrayList<LogInterceptor>()
            }

            /**
             * 设置默认标签
             */
            fun setTag(tag: String): Builder {
                this.tag = tag
                return this
            }

            /**
             * 展示边框
             */
            fun showBorder(): Builder {
                this.withBorder = true
                return this
            }

            /**
             * 隐藏边框
             */
            fun hideBorder(): Builder {
                this.withBorder = false
                return this
            }

            /**
             * 展示线程信息
             */
            fun showThread(): Builder {
                this.withThread = true
                return this
            }

            /**
             * 隐藏线程信息
             */
            fun hideThread(): Builder {
                this.withThread = false
                return this
            }

            /**
             * 展示堆栈跟踪信息
             */
            fun showStackTrace(): Builder {
                this.withStackTrace = true
                return this
            }

            /**
             * 隐藏堆栈跟踪信息
             */
            fun hideStackTrace(): Builder {
                this.withStackTrace = false
                return this
            }

            /**
             * 设置堆栈跟踪信息深度
             */
            fun setStackTraceDepth(depth: Int): Builder {
                this.stackTraceDepth = depth
                return this
            }

            /**
             * 添加日志拦截器
             * @param interceptor 拦截器
             */
            fun addInterceptors(interceptor: LogInterceptor): Builder {
                this.interceptors.add(interceptor)
                return this
            }

            /**
             * 构建日志配置
             */
            fun build(): LogConfiguration {
                return LogConfiguration(this)
            }
        }
    }

}