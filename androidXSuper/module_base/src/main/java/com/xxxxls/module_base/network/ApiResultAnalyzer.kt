package com.xxxxls.module_base.network

import com.xxxxls.xsuper.adapter.ResultAnalyzer
import com.xxxxls.xsuper.component.bridge.ComponentActionBridge
import com.xxxxls.xsuper.model.SuperResult
import com.xxxxls.xsuper.model.isSuccess
import com.xxxxls.xsuper.model.toFailureResult

/**
 * 接口 响应结果分析处理器
 * @author Max
 * @date 2020/11/30.
 */
class ApiResultAnalyzer private constructor() : ResultAnalyzer {

    companion object {
        val instance: ApiResultAnalyzer by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            ApiResultAnalyzer()
        }
    }

    /**
     * 分析结果
     * @param bridge 与组件的通信桥梁
     * @param result 结果
     */
    override suspend fun <T> analysisResult(
        bridge: ComponentActionBridge,
        result: SuperResult<T>
    ): SuperResult<T> {
        return if (result.isSuccess()) {
            // 成功的响应，无需额外拦截处理
            result
        } else {
            // 失败的响应，交给异常分析器处理
            val exception =
                analysisException(
                    bridge,
                    (result as SuperResult.Failure).throwable
                )
            exception.toFailureResult()
        }
    }

    override suspend fun analysisException(bridge: ComponentActionBridge, throwable: Throwable): Throwable {
        return ApiExceptionAnalyzer.analysisException(bridge, throwable)
    }

}