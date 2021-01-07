package com.xxxxls.xsuper.repository

import com.xxxxls.xsuper.adapter.*
import com.xxxxls.xsuper.model.XSuperResponse
import com.xxxxls.xsuper.model.XSuperResult
import com.xxxxls.xsuper.model.toFailureResult
import com.xxxxls.xsuper.model.toSuccessResult
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

    // 默认异常转换器
    private val defaultExceptionConverter: ExceptionConverter by lazy {
        DefaultExceptionConverter()
    }

    /**
     * 获取适配器
     */
    open fun getResponseAdapter(): ResponseAdapter {
        return defaultResponseAdapter
    }

    /**
     * 获取异常转换器
     */
    open fun getExceptionConverter(): ExceptionConverter {
        return defaultExceptionConverter
    }

    /**
     * API-请求
     * @param converter 异常转换器
     * @param adapter 响应转换器
     * @param block 发起请求
     */
    fun <T> apiFlow(
        converter: ExceptionConverter = getExceptionConverter(),
        adapter: ResponseAdapter = getResponseAdapter(),
        context: CoroutineContext = Dispatchers.IO,
        block: suspend () -> XSuperResponse<T>
    ): Flow<XSuperResult<T>> {
        return flow {
            emit(adapter.responseToResult(block.invoke()))
        }.catch {
            emit(adapter.throwableToResult(converter.convert(it)))
        }.flowOn(context)
    }

    /**
     * 转换为Flow（Api）
     * @param converter 异常转换器
     * @param adapter 响应适配器
     */
    fun <T> XSuperResponse<T>.asApiFlow(
        converter: ExceptionConverter = getExceptionConverter(),
        adapter: ResponseAdapter = getResponseAdapter(),
        context: CoroutineContext = Dispatchers.IO
    ): Flow<XSuperResult<T>> {
        return flow {
            emit(adapter.responseToResult(this@asApiFlow))
        }.catch {
            emit(adapter.throwableToResult(converter.convert(it)))
        }.flowOn(context)
    }

    /**
     * 发起接口请求
     * @param converter 异常转换器
     */
    suspend fun <T> apiResult(
        converter: ExceptionConverter = getExceptionConverter(),
        adapter: ResponseAdapter = getResponseAdapter(),
        block: suspend () -> XSuperResponse<T>
    ): XSuperResult<T> {
        return try {
            adapter.responseToResult(block.invoke())
        } catch (e: Exception) {
            adapter.throwableToResult(converter.convert(e))
        }
    }

    /**
     * 转换为Result（Api）
     * @param converter 异常转换器
     * @param adapter 响应适配器
     */
    fun <T> XSuperResponse<T>.asApiResult(
        converter: ExceptionConverter = getExceptionConverter(),
        adapter: ResponseAdapter = getResponseAdapter()
    ): XSuperResult<T> {
        return try {
            adapter.responseToResult(this)
        } catch (e: Exception) {
            adapter.throwableToResult(converter.convert(e))
        }
    }

    /**
     * 结果Flow
     * @param block 发起请求
     * @param converter 异常转换器
     */
    fun <T> resultFlow(
        converter: ExceptionConverter = getExceptionConverter(),
        context: CoroutineContext = Dispatchers.IO,
        block: suspend () -> T
    ): Flow<XSuperResult<T>> {
        return flow<XSuperResult<T>> {
            emit(block.invoke().toSuccessResult())
        }.catch {
            emit(converter.convert(it).toFailureResult())
        }.flowOn(context)
    }
}