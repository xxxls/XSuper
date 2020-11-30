package com.xxxxls.xsuper.repository

import com.xxxxls.xsuper.adapter.DefaultResponseAdapter
import com.xxxxls.xsuper.adapter.ResponseAdapter
import com.xxxxls.xsuper.model.XSuperResponse
import com.xxxxls.xsuper.model.XSuperResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlin.coroutines.CoroutineContext

/**
 * Repository
 * @author Max
 * @date 2020/11/28.
 */
open class XSuperRepository {

    // 默认适配器
    private val defaultResponseAdapter: ResponseAdapter by lazy {
        DefaultResponseAdapter()
    }

    /**
     * 获取适配器
     */
    open fun getResponseAdapter(): ResponseAdapter {
        return defaultResponseAdapter
    }

    /**
     * API-请求
     * @param responseAdapter 响应转换器
     * @param block 发起请求
     */
    fun <T> apiFlow(
        responseAdapter: ResponseAdapter = getResponseAdapter(),
        context: CoroutineContext = Dispatchers.IO,
        block: suspend () -> XSuperResponse<T>
    ): Flow<XSuperResult<T>> {
        return flow {
            emit(responseAdapter.responseToResult(block.invoke()))
        }.catch {
            emit(responseAdapter.throwableToResult(it))
        }.flowOn(context)
    }

    /**
     * 发起接口请求
     */
    suspend fun <T> apiCall(
        responseAdapter: ResponseAdapter = getResponseAdapter(),
        block: suspend () -> XSuperResponse<T>
    ): XSuperResult<T> {
        return try {
            responseAdapter.responseToResult(block.invoke())
        } catch (e: Exception) {
            responseAdapter.throwableToResult(e)
        }
    }
}