package com.xxxxls.xsuper.adapter

import com.xxxxls.xsuper.component.bridge.ComponentActionBridge

/**
 * 异常解析器
 * @author Max
 * @date 2020/11/30.
 */
interface ExceptionAnalyzer {

    /**
     * 解析异常
     * @param bridge 组件沟通桥梁
     * @param throwable 异常信息
     */
    suspend fun analysisException(
        bridge: ComponentActionBridge, throwable: Throwable
    ): Throwable
}