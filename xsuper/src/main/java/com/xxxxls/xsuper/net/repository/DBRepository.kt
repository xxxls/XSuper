package com.xxxxls.xsuper.net.repository

import com.xxxxls.xsuper.exceptions.XSuperException
import com.xxxxls.xsuper.net.callback.XSuperCallBack
import kotlinx.coroutines.*

/**
 * 数据库
 * @author Max
 * @date 2019-12-04.
 */
abstract class DBRepository<Dao> : XSuperRepository() {

    protected val dbService: Dao by lazy {
        getDao()
    }

    protected abstract fun getDao(): Dao

    /**
     * 转换协程
     */
    protected fun <R> wrap(
        runnable: (dao: Dao) -> R
    ): Deferred<R> {
        return GlobalScope.async(Dispatchers.IO) {
            runnable(dbService)
        }
    }

    /**
     * 发起数据库操作
     * @param callback 结果回调
     * @param service 操作接口
     */
    protected fun <T> requestDB(
        callback: XSuperCallBack<T>,
        service: (dao: Dao) -> T
    ): Job {
        return wrap(service).request(callback = callback)
    }

    /**
     * 协程执行数据库操作，并把结果给callback
     * @param callback 回调
     */
    protected inline fun <R> Deferred<R>.request(
        callback: XSuperCallBack<R>
    ): Job {
        callback.showLoading()
        return this@DBRepository.launch {
            try {
                val result = this@request.await()
                callback.onSuccess(result)
            } catch (e: Exception) {
                e.printStackTrace()
                callback.onError(requestExceptionConversion(e))
            } finally {
                callback.dismissLoading()
            }
        }
    }


    /**
     * 请求异常转换
     * @param throwable 异常信息
     * @return 转换为可读异常
     */
    protected open fun requestExceptionConversion(throwable: Throwable): XSuperException{
        return XSuperException(0, throwable.message ?: throwable.toString(), throwable)
    }

}