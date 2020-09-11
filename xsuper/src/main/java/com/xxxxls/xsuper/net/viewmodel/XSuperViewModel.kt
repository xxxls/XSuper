package com.xxxxls.xsuper.net.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xxxxls.xsuper.net.XSuperResult
import com.xxxxls.xsuper.net.bridge.ComponentAction
import com.xxxxls.xsuper.net.bridge.IComponentBridge
import com.xxxxls.xsuper.net.callback.XSuperCallBack
import com.xxxxls.xsuper.net.repository.XSuperRepository
import kotlinx.coroutines.*
import java.util.concurrent.ConcurrentHashMap
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

/**
 * super - ViewModel
 * @author Max
 * @date 2019-11-29.
 */
open class XSuperViewModel : ViewModel(), IComponentBridge {

    /**
     * 与组件(activity，fragment)的通信（加载弹窗，toast等）
     */
    val componentBridgeLiveData: MutableLiveData<ComponentAction> by lazy {
        MutableLiveData<ComponentAction>()
    }

    //Repository集合
    protected val repositorys: ConcurrentHashMap<Class<*>, XSuperRepository> by lazy {
        ConcurrentHashMap<Class<*>, XSuperRepository>()
    }

//    //主Repository
//    val mRepository: R by lazy {
//        val clazz = (ClassUtils.getSuperClassGenericType<R>(javaClass))
//        val repository = clazz.newInstance()
//        mRepositorys[clazz] = repository
//        repository
//    }

    /**
     * 创建Repository
     */
    protected inline fun <reified T : XSuperRepository> createRepository(): T {
        var repository = repositorys[T::class.java] as? T
        if (repository == null) {
            repository = T::class.java.newInstance()
            addRepository(repository)
        }
        return repository
    }

    /**
     * 添加Repository至本viewModel
     */
    protected inline fun <reified T : XSuperRepository> addRepository(repository: T): T {
        repository.setComponentBridge(this)
        repositorys[T::class.java] = repository
        return repository
    }

    /**
     * 启动一个协程任务
     */
    fun launch(
        context: CoroutineContext = EmptyCoroutineContext,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> Unit
    ): Job {
        return viewModelScope.launch(context, start, block)
    }

    override fun onCleared() {
        super.onCleared()
        repositorys.forEach {
            it.value.onCleared()
        }
    }

    /**
     * 中转给组件
     */
    override fun onAction(action: ComponentAction) {
        componentBridgeLiveData.postValue(action)
    }

}
