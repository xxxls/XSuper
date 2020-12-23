package com.xxxxls.xsuper.model

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.xxxxls.xsuper.callback.XSuperCallBack

/**
 * result - LiveData
 * @author Max
 * @date 2019-11-30.
 */
open class ResultLiveData<T> : MutableLiveData<XSuperResult<T>>(),
    XSuperCallBack<T> {

    override fun onSuccess(result: T) {
        postValue(XSuperResult.Success(result))
    }

    override fun onFailure(throwable: Throwable) {
        postValue(XSuperResult.Failure(throwable))
    }

    /**
     * 添加观察者
     * @param owner 生命周期
     * @param success 成功回调
     * @param failure 失败回调
     */
    fun observe(
        owner: LifecycleOwner,
        success: (value: T) -> Unit = {},
        failure: (e: Throwable) -> Unit = {}
    ): ResultLiveData<T> {
        super.observe(owner, Observer<XSuperResult<T>> {
            when (it) {
                is XSuperResult.Failure -> {
                    failure(it.throwable)
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
     * @param failure 失败回调
     */
    fun observeForever(
        success: (value: T) -> Unit = {},
        failure: (e: Throwable) -> Unit = {}
    ): ResultLiveData<T> {
        super.observeForever {
            when (it) {
                is XSuperResult.Failure -> {
                    failure(it.throwable)
                }
                is XSuperResult.Success -> {
                    success(it.data)
                }
            }
        }
        return this
    }


}