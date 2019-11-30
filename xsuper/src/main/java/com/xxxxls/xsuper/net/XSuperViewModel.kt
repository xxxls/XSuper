package com.xxxxls.xsuper.net

import androidx.lifecycle.ViewModel
import com.xxxxls.xsuper.util.ClassUtils
import java.util.concurrent.ConcurrentHashMap

/**
 * super - ViewModel
 * @author Max
 * @date 2019-11-29.
 */
open class XSuperViewModel<R : XSuperRepository> : ViewModel() {

    //Repository集合
    protected val mRepositorys: ConcurrentHashMap<Class<*>, XSuperRepository> by lazy {
        ConcurrentHashMap<Class<*>, XSuperRepository>()
    }

    //主Repository
    val mRepository: R by lazy {
        val clazz = (ClassUtils.getSuperClassGenericType<R>(javaClass))
        val repository = clazz.newInstance()
        mRepositorys[clazz] = repository
        repository
    }

    /**
     * 创建Repository
     */
    inline fun <reified T : XSuperRepository> createRepository(): T {
        var repository = mRepositorys[T::class.java] as? T
        if (repository == null) {
            repository = T::class.java.newInstance()
            mRepositorys[T::class.java] = repository
        }
        return repository
    }

    override fun onCleared() {
        super.onCleared()
        mRepositorys.forEach {
            it.value.onCleared()
        }
    }
}
