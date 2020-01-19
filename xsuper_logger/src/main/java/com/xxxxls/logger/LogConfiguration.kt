package com.xxxxls.logger

import com.xxxxls.logger.formatter.border.DefaultBorderFormatter
import com.xxxxls.logger.formatter.stacktrace.DefaultStackTraceFormatter
import com.xxxxls.logger.formatter.thread.DefaultThreadFormatter
import com.xxxxls.logger.formatter.thread.ThreadFormatter
import com.xxxxls.logger.interceptor.BorderInterceptor
import com.xxxxls.logger.interceptor.Interceptor
import com.xxxxls.logger.interceptor.StackTraceInterceptor
import com.xxxxls.logger.interceptor.ThreadInterceptor


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

//    /**
//     * 是否展示日志边框
//     */
//    val withBorder: Boolean
//
//    /**
//     * 是否展示日志线程信息
//     */
//    val withThread: Boolean
//
//    /**
//     * 是否展示堆栈跟踪信息
//     */
//    val withStackTrace: Boolean
//
//    /**
//     * 堆栈跟踪信息深度
//     */
//    val stackTraceDepth: Int

    /**
     * 日志拦截器
     */
    val interceptors: MutableList<Interceptor>?

    init {
        this.tag = builder.tag
//        this.withBorder = builder.withBorder
//        this.withThread = builder.withThread
//        this.withStackTrace = builder.withStackTrace
//        this.stackTraceDepth = builder.stackTraceDepth
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
             * 堆栈跟踪信息深度
             */
            internal var stackTraceDepth: Int = 0

            /**
             * 拦截器
             */
            internal val interceptors: MutableList<Interceptor> by lazy {
                ArrayList<Interceptor>()
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
             * 设置堆栈跟踪信息深度
             */
            fun setStackTraceDepth(depth: Int): Builder {
                this.stackTraceDepth = depth
                return this
            }

            /**
             * 添加日志拦截器
             * @param interceptor 拦截器
             * @param index 插入的位置PS：为空时默认插入到列表末尾
             */
            fun addInterceptors(interceptor: Interceptor, index: Int? = null): Builder {
                if (index != null) {
                    this.interceptors.add(index, interceptor)
                } else {
                    this.interceptors.add(interceptor)
                }
                return this
            }

            /**
             * 构建日志配置
             */
            fun build(): LogConfiguration {
                //添加堆栈信息拦截器
                addInterceptors(
                    StackTraceInterceptor(
                        stackTraceDepth,
                        DefaultStackTraceFormatter()
                    )
                    , 0
                )

                //添加线程信息拦截器
                addInterceptors(ThreadInterceptor(withThread, DefaultThreadFormatter()), 0)

                //添加边框拦截器（添加至末尾）
                addInterceptors(BorderInterceptor(withBorder, DefaultBorderFormatter()))
                return LogConfiguration(this)
            }
        }
    }

}