package com.xxxxls.xsuper.net

import androidx.lifecycle.ViewModel
import com.xxxxls.xsuper.util.ClassUtils

/**
 * super - ViewModel
 * @author Max
 * @date 2019-11-29.
 */
open class XSuperViewModel<R : XSuperRepository> : ViewModel() {

    val mRepository: R by lazy {
        createRepository()
    }

    inline fun <reified R : XSuperRepository> getRepository(): R {
        return createRepository<R>()
    }

    /**
     * 创建Repository
     */
    protected inline fun <reified T> createRepository(): T {
        return (ClassUtils.getSuperClassGenericType<T>(T::class.java))
            .newInstance()
    }

    /**
     * 创建Repository
     */
    protected open fun createRepository(): R {
        return (ClassUtils.getSuperClassGenericType<R>(javaClass))
            .newInstance()
    }

    override fun onCleared() {
        super.onCleared()
    }
}