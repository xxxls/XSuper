package com.xxxxls.xsuper.adapter

import com.xxxxls.xsuper.component.bridge.ComponentActionBridge
import com.xxxxls.xsuper.model.XSuperResult

/**
 * 结果分析器
 * @author Max
 * @date 2020/11/30.
 */
interface ResultAnalyzer : ExceptionAnalyzer {

    /**
     * 分析结果
     * @param bridge 与组件的通信桥梁
     * @param result 结果
     */
    suspend fun <T> analysisResult(
        bridge: ComponentActionBridge,
        result: XSuperResult<T>
    ): XSuperResult<T>
}