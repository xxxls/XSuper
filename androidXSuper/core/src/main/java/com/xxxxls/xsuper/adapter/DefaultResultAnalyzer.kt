package com.xxxxls.xsuper.adapter

import com.xxxxls.xsuper.component.bridge.ComponentActionBridge
import com.xxxxls.xsuper.model.SuperResult

/**
 * 默认结果分析器
 * @author Max
 * @date 2020/11/30.
 */
open class DefaultResultAnalyzer : ResultAnalyzer {

    /**
     * 分析结果
     */
    override suspend fun <T> analysisResult(
        bridge: ComponentActionBridge,
        result: SuperResult<T>
    ): SuperResult<T> {
        return result
    }

    /**
     * 分析异常
     */
    override suspend fun analysisException(bridge: ComponentActionBridge, throwable: Throwable): Throwable {
        return throwable
    }

}