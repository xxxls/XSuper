package com.xxxxls.xsuper.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xxxxls.xsuper.adapter.DefaultResultAnalyzer
import com.xxxxls.xsuper.adapter.ExceptionAnalyzer
import com.xxxxls.xsuper.adapter.ResultAnalyzer
import com.xxxxls.xsuper.callback.XSuperCallBack
import com.xxxxls.xsuper.component.ICleared
import com.xxxxls.xsuper.loading.*
import com.xxxxls.xsuper.component.bridge.ComponentAction
import com.xxxxls.xsuper.component.bridge.ComponentActionBridge
import com.xxxxls.xsuper.model.ResultLiveData
import com.xxxxls.xsuper.model.XSuperResult
import com.xxxxls.xsuper.model.toFailureResult
import com.xxxxls.xsuper.model.toSuccessResult
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import java.util.concurrent.ExecutionException
import kotlin.coroutines.CoroutineContext

/**
 * super - ViewModel
 * @author Max
 * @date 2019-11-29.
 */
open class XSuperViewModel : ViewModel(), ComponentActionBridge, ILoading, ICleared {

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
        callBack: XSuperCallBack<T>,
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
        block: suspend () -> Flow<XSuperResult<T>>
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
     * 中转给组件
     */
    override fun sendAction(action: ComponentAction) {
        componentBridgeLiveData.postValue(action)
    }

    /**
     * 对响应结果 进行分析处理
     * @param analyzer 响应结果分析处理器
     * @param bridge 与组件通信
     */
    protected open fun <T> analysisResult(
        result: XSuperResult<T>,
        analyzer: ResultAnalyzer = getResultAnalyzer(),
        bridge: ComponentActionBridge = this
    ): XSuperResult<T> {
        return analyzer.analysisResult(bridge, result)
    }

    /**
     * 对异常 进行分析处理
     * @param analyzer 响应结果分析处理器
     * @param bridge 与组件通信
     */
    protected open fun analysisException(
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
