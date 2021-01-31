package com.xxxxls.xsuper.model

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.xxxxls.xsuper.callback.SuperCallBack

/**
 * result - LiveData
 * @author Max
 * @date 2019-11-30.
 */
open class ResultLiveData<T> : MutableLiveData<SuperResult<T>>(),
    SuperCallBack<T> {

    override fun onSuccess(result: T) {
        postValue(SuperResult.Success(result))
    }

    override fun onFailure(throwable: Throwable) {
        postValue(SuperResult.Failure(throwable))
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
        super.observe(owner, Observer<SuperResult<T>> {
            when (it) {
                is SuperResult.Failure -> {
                    failure(it.throwable)
                }
                is SuperResult.Success -> {
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
                is SuperResult.Failure -> {
                    failure(it.throwable)
                }
                is SuperResult.Success -> {
                    success(it.data)
                }
            }
        }
        return this
    }


}