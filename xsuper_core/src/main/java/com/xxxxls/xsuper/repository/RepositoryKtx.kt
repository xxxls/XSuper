package com.xxxxls.xsuper.repository

import com.xxxxls.xsuper.adapter.DefaultResponseAdapter
import com.xxxxls.xsuper.adapter.ResponseAdapter
import com.xxxxls.xsuper.model.XSuperResponse
import com.xxxxls.xsuper.model.XSuperResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlin.coroutines.CoroutineContext


/**
 * API-请求
 * @param responseAdapter 响应转换器
 * @param block 发起请求
 */
inline fun <T> apiFlow(
    responseAdapter: ResponseAdapter = DefaultResponseAdapter(),
    context: CoroutineContext = Dispatchers.IO,
    crossinline block: suspend () -> XSuperResponse<T>
): Flow<XSuperResult<T>> {
    return flow {
        emit(responseAdapter.responseToResult(block.invoke()))
    }.catch {
        emit(responseAdapter.throwableToResult(it))
    }.flowOn(context)
}