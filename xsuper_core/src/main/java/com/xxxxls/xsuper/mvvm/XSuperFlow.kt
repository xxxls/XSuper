package com.xxxxls.xsuper.mvvm

import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.AbstractFlow
import kotlinx.coroutines.flow.FlowCollector

/**
 * Flow
 * @author Max
 * @date 2020/11/26.
 */
@FlowPreview
open class XSuperFlow<T>(private val block: suspend FlowCollector<T>.() -> Unit) :
    AbstractFlow<T>() {
    override suspend fun collectSafely(collector: FlowCollector<T>) {
        collector.block()
    }
}