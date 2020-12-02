package com.xxxxls.xsuper.adapter

import com.xxxxls.xsuper.component.bridge.ComponentActionBridge
import com.xxxxls.xsuper.exceptions.XSuperException
import com.xxxxls.xsuper.model.XSuperResult

/**
 * 默认结果分析器
 * @author Max
 * @date 2020/11/30.
 */
open class DefaultResultAnalyzer : ResultAnalyzer {

    /**
     * 分析结果
     */
    override fun <T> analysisResult(
        bridge: ComponentActionBridge,
        result: XSuperResult<T>
    ): XSuperResult<T> {
        return result
    }

    override fun analysisException(bridge: ComponentActionBridge, throwable: Throwable): Exception {
        return if (throwable is Exception) {
            throwable
        } else {
            XSuperException(throwable)
        }
    }

}