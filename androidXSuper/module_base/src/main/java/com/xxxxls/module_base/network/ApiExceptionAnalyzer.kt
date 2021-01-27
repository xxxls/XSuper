package com.xxxxls.module_base.network

import android.app.Activity
import android.app.Dialog
import com.xxxxls.module_base.component.FinishAction
import com.xxxxls.module_base.network.response.BaseResponse
import com.xxxxls.utils.L
import com.xxxxls.xsuper.adapter.ExceptionAnalyzer
import com.xxxxls.xsuper.component.bridge.ComponentAction
import com.xxxxls.xsuper.component.bridge.ComponentActionBridge
import com.xxxxls.xsuper.exceptions.ApiException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.*

/**
 * 异常分析器
 * @author Max
 * @date 2020/11/28.
 */
object ApiExceptionAnalyzer : ExceptionAnalyzer {

    /**
     * 解析异常
     */
    override suspend fun analysisException(
        bridge: ComponentActionBridge, throwable: Throwable
    ): Throwable {
        return when (throwable) {
            is ApiException -> {
                /*接口异常*/
                withContext(Dispatchers.Main) {
                    analysisApiException(
                        bridge,
                        throwable
                    )
                }
            }
            else -> {
                /*其他异常*/
                SafeExceptionConverter.instance.convert(throwable)
            }
        }
    }

    /**
     * 解析接口异常
     */
    private fun analysisApiException(
        bridge: ComponentActionBridge,
        apiException: ApiException
    ): Exception {
        (apiException.response as? BaseResponse<*>)?.let {
            when (it.code) {
                1 -> {
                    // 登录已过期，提示重新登录
                    bridge.sendAction(ComponentAction.BuildDialog { activity ->
                        if (activity == null) {
                            return@BuildDialog null
                        }
                        return@BuildDialog createDialog(
                            activity
                        )
                    })
                }
                2 -> {
                    // 账号被其他设备登录，退出登录
                    bridge.sendAction(ComponentAction.BuildDialog { activity ->
                        if (activity == null) {
                            return@BuildDialog null
                        }
                        return@BuildDialog createDialog(
                            activity
                        )
                    })
                }
            }
        }
        return apiException
    }

    /**
     * 创建Dialog
     */
    private fun createDialog(activity: Activity): Dialog {
        return androidx.appcompat.app.AlertDialog.Builder(activity).apply {
            setTitle("模拟Title")
            setNegativeButton("No") { dialog, which ->
                dialog.dismiss()
            }
            setNegativeButton("Yes") { dialog, which ->
                dialog.dismiss()
            }
        }.create()
    }

}