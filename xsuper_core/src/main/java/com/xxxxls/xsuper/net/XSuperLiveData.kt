package com.xxxxls.xsuper.net

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.xxxxls.xsuper.exceptions.XSuperException
import com.xxxxls.xsuper.net.callback.XSuperCallBack

/**
 * super - LiveData
 * @author Max
 * @date 2019-11-30.
 */
open class XSuperLiveData<T> : MutableLiveData<XSuperResult<T>>(),
    XSuperCallBack<T> {

    override fun onSuccess(result: T?) {
        postValue(XSuperResult.Success(result))
    }

    override fun onError(exception: XSuperException) {
        postValue(XSuperResult.Failure(exception))
    }

    /**
     * 添加观察者
     * @param owner 生命周期
     * @param success 成功回调
     * @param error 失败回调
     */
    fun observe(
        owner: LifecycleOwner,
        success: (value: T?) -> Unit = {},
        error: (e: XSuperException) -> Unit = {}
    ): XSuperLiveData<T> {
        super.observe(owner, Observer<XSuperResult<T>> {
            when (it) {
                is XSuperResult.Failure -> {
                    error(it.exception)
                }
                is XSuperResult.Success -> {
                    success(it.data)
                }
            }
        })
        return this
    }

    /**
     * 添加观察者
     * @param success 成功回调
     * @param error 失败回调
     */
    fun observeForever(
        success: (value: T?) -> Unit = {},
        error: (e: XSuperException) -> Unit = {}
    ): XSuperLiveData<T> {
        super.observeForever {
            when (it) {
                is XSuperResult.Failure -> {
                    error(it.exception)
                }
                is XSuperResult.Success -> {
                    success(it.data)
                }
            }
        }
        return this
    }


}