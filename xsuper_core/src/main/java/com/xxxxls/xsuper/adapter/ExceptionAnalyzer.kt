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
     */
    fun analysisException(
        bridge: ComponentActionBridge, throwable: Throwable
    ): Exception
}