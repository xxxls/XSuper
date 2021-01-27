package com.xxxxls.xsuper.repository

import com.xxxxls.xsuper.adapter.*
import com.xxxxls.xsuper.component.ICleared
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
open class XSuperRepository : ICleared {

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
    protected open fun getResponseAdapter(): ResponseAdapter {
        return defaultResponseAdapter
    }

    /**
     * 获取异常转换器
     */
    protected open fun getExceptionConverter(): ExceptionConverter {
        return defaultExceptionConverter
    }

    /**
     * Flow - 接口
     * @param adapter 响应适配器
     * @param converter 异常转换器
     * @param context 协程上下文
     * @param block 发起请求
     */
    fun <T> flowApi(
        adapter: ResponseAdapter = getResponseAdapter(),
        converter: ExceptionConverter = getExceptionConverter(),
        context: CoroutineContext = Dispatchers.IO,
        block: suspend () -> XSuperResponse<T>
    ): Flow<T> {
        return flow {
            // 拿取响应体
            val response = block.invoke()
            // 响应出去，需要捕获异常
            emit(adapter.getResponseBody(response))
        }.catch {
            throw converter.convert(it)
        }.flowOn(context)
    }

    /**
     * Flow - 接口 - 结果
     * @param converter 异常转换器
     * @param adapter 响应适配器
     * @param context 协程上下文
     * @param block 发起请求
     */
    fun <T> flowApiResult(
        adapter: ResponseAdapter = getResponseAdapter(),
        converter: ExceptionConverter = getExceptionConverter(),
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
     * 接口 - 转 - Result
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
     * Response - 转 - Result
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
     * Flow - Result
     * @param block 发起请求
     * @param converter 异常转换器
     */
    fun <T> flowResult(
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


    /**
     * Flow 安全处理（异常处理）
     *
     * @param block 发起请求
     * @param converter 异常转换器
     */
    fun <T> flowSafety(
        converter: ExceptionConverter = getExceptionConverter(),
        context: CoroutineContext = Dispatchers.IO,
        block: suspend () -> T
    ): Flow<T> {
        return flow {
            emit(block.invoke())
        }.catch {
            throw (converter.convert(it))
        }.flowOn(context)
    }
}