package com.xxxxls.xsuper.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xxxxls.xsuper.adapter.DefaultResultAnalyzer
import com.xxxxls.xsuper.adapter.ExceptionAnalyzer
import com.xxxxls.xsuper.adapter.ResultAnalyzer
import com.xxxxls.xsuper.callback.SuperCallBack
import com.xxxxls.xsuper.component.ICleared
import com.xxxxls.xsuper.loading.*
import com.xxxxls.xsuper.component.bridge.ComponentAction
import com.xxxxls.xsuper.component.bridge.ComponentActionBridge
import com.xxxxls.xsuper.model.ResultLiveData
import com.xxxxls.xsuper.model.SuperResult
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import kotlin.coroutines.CoroutineContext

/**
 * super - ViewModel
 * @author Max
 * @date 2019-11-29.
 */
open class SuperViewModel : ViewModel(), ComponentActionBridge, ILoading, ICleared {

    /**
     * 与组件(activity，fragment)的通信（加载弹窗，toast等）
     */
    val componentBridgeLiveData: MutableLiveData<ComponentAction> by lazy {
        MutableLiveData<ComponentAction>()
    }

    // 默认分析器
    private val defaultResultAnalyzer: ResultAnalyzer by lazy {
        DefaultResultAnalyzer()
    }

    /**
     * 获取分析器
     */
    protected open fun getResultAnalyzer(): ResultAnalyzer {
        return defaultResultAnalyzer
    }

    /**
     *
     * 启动一个协程任务 (Flow)
     * @param callBack callBack/LiveData
     * @param loading 执行进度弹窗
     * @param analyzer 响应结果分析处理器
     * @param context 默认IO
     * @param start 默认启动方式
     * @param block 执行的任务
     */
    fun <T> launchF(
        callBack: SuperCallBack<T>,
        loading: ILoading? = this,
        analyzer: ExceptionAnalyzer = getResultAnalyzer(),
        context: CoroutineContext = Dispatchers.IO,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend () -> Flow<T>
    ): Job {

        return viewModelScope.launch(
            context = context,
            start = start,
            block = {
                block.invoke()
                    .onStart {
                        loading.showLoadingInMain(this.hashCode())
                    }.onCompletion {
                        loading.dismissLoadingInMain(this.hashCode())
                    }.catch {
                        // 失败，异常分析处理
                        callBack.onFailure(
                            analysisException(
                                throwable = it,
                                analyzer = analyzer
                            )
                        )
                    }.collectLatest {
                        // 成功，通知出去
                        callBack.onSuccess(it)
                    }
            })
    }


    /**
     *
     * 启动一个协程任务 (Flow - Result)
     * @param liveData LiveData
     * @param loading 执行进度弹窗
     * @param analyzer 响应结果分析处理器
     * @param context 默认IO
     * @param start 默认启动方式
     * @param block 执行的任务
     */
    fun <T> launchFR(
        liveData: ResultLiveData<T>,
        loading: ILoading? = this,
        analyzer: ResultAnalyzer = getResultAnalyzer(),
        context: CoroutineContext = Dispatchers.IO,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend () -> Flow<SuperResult<T>>
    ): Job {
        return viewModelScope.launch(
            context = context,
            start = start,
            block = {
                block.invoke()
                    .onStart {
                        loading.showLoadingInCoroutine(this.hashCode())
                    }.onCompletion {
                        loading.dismissLoadingInCoroutine(this.hashCode())
                    }.catch {
                        // 失败，异常分析处理
                        liveData.onFailure(
                            analysisException(
                                throwable = it,
                                analyzer = analyzer
                            )
                        )
                    }.collectLatest {
                        // 成功，通知出去
                        liveData.postValue(
                            analysisResult(
                                result = it,
                                analyzer = analyzer
                            )
                        )
                    }
            })
    }


    /**
     * 启动一个协程任务 (普通)
     * @param loading 执行进度弹窗，null表示不使用弹窗
     * @param context 默认IO
     * @param start 默认启动方式
     * @param block 执行的任务
     */
    @UseExperimental(InternalCoroutinesApi::class)
    fun launch(
        loading: ILoading? = this,
        context: CoroutineContext = Dispatchers.IO,
        start: CoroutineStart = CoroutineStart.DEFAULT,
        block: suspend CoroutineScope.() -> Unit
    ): Job {
        return viewModelScope.launch(context = context, start = start, block = {
            loading.showLoadingInCoroutine(hashCode())
            block.invoke(this)
        }).apply {
            loading?.run {
                invokeOnCompletion(onCancelling = true, invokeImmediately = true, handler = {
                    // 任务取消
                    this.dismissLoadingInMain(this.hashCode())
                })
                invokeOnCompletion(onCancelling = false, invokeImmediately = true, handler = {
                    // 任务完成
                    this.dismissLoadingInMain(this.hashCode())
                })
            }
        }
    }

    override fun showLoading(id: Int?, message: CharSequence?) {
        this.sendAction(ComponentAction.ShowLoading(id, message))
    }

    override fun dismissLoading(id: Int?) {
        this.sendAction(ComponentAction.DismissLoading(id))
    }

    /**
     * 中转给组件-发送命令
     * 注意：必须在主线程中调用此函数
     */
    override fun sendAction(action: ComponentAction) {
        componentBridgeLiveData.value = action
    }

    /**
     * 对响应结果 进行分析处理
     * @param analyzer 响应结果分析处理器
     * @param bridge 与组件通信
     */
    protected open suspend fun <T> analysisResult(
        result: SuperResult<T>,
        analyzer: ResultAnalyzer = getResultAnalyzer(),
        bridge: ComponentActionBridge = this
    ): SuperResult<T> {
        return analyzer.analysisResult(bridge, result)
    }

    /**
     * 对异常 进行分析处理
     * @param analyzer 响应结果分析处理器
     * @param bridge 与组件通信
     */
    protected open suspend fun analysisException(
        throwable: Throwable,
        analyzer: ExceptionAnalyzer = getResultAnalyzer(),
        bridge: ComponentActionBridge = this
    ): Throwable {
        return analyzer.analysisException(bridge, throwable)
    }

    override fun onCleared() {
        super<ViewModel>.onCleared()
    }
}
