package com.xxxxls.module_base.support

import android.app.Activity
import android.app.Dialog
import android.net.ParseException
import com.google.gson.JsonParseException
import com.xxxxls.module_base.R
import com.xxxxls.module_base.response.BaseResponse
import com.xxxxls.utils.AppUtils
import com.xxxxls.xsuper.component.bridge.ComponentAction
import com.xxxxls.xsuper.component.bridge.ComponentActionBridge
import com.xxxxls.xsuper.exceptions.ApiException
import com.xxxxls.xsuper.exceptions.CodeException
import com.xxxxls.xsuper.exceptions.NetWorkException
import org.json.JSONException
import retrofit2.HttpException
import java.net.*
import java.util.concurrent.TimeoutException

/**
 * 异常分析器
 * @author Max
 * @date 2020/11/28.
 */
object ExceptionAnalyzer {

    private val httpExceptionMsg = AppUtils.getApp().getString(R.string.base_exception_network)
    private val connectExceptionMsg = AppUtils.getApp().getString(R.string.base_exception_connect)
    private val jsonExceptionMsg = AppUtils.getApp().getString(R.string.base_exception_parse_json)
    private val unknownHostExceptionMsg =
        AppUtils.getApp().getString(R.string.base_exception_parse_host)
    private val codeExceptionMsg = AppUtils.getApp().getString(R.string.base_exception_code)


    /**
     * 解析异常
     */
    fun analysisException(
        bridge: ComponentActionBridge, throwable: Throwable
    ): Exception {
        return when (throwable) {
            is ApiException -> {
                /*接口异常*/
                analysisApiException(bridge, throwable)
            }
            is HttpException -> {
                /*网络异常*/
                NetWorkException(httpExceptionMsg, throwable)
            }
            is TimeoutException, is SocketTimeoutException, is ConnectException, is SocketException -> {
                /*网络超时异常*/
                /*链接异常*/
                NetWorkException(connectExceptionMsg, throwable)
            }
            is UnknownHostException -> {
                /*无法解析该域名异常*/
                NetWorkException(unknownHostExceptionMsg, throwable)
            }
            is JsonParseException, is JSONException, is ParseException -> {
                /*json解析异常*/
                CodeException(jsonExceptionMsg, throwable)
            }
            else -> {
                /*其他异常*/
                CodeException(codeExceptionMsg, throwable)
            }
        }
    }

    /**
     * 解析接口异常
     */
    fun analysisApiException(
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
                        return@BuildDialog createDialog(activity)
                    })
                }
                2 -> {
                    // 账号被其他设备登录，退出登录
                    bridge.sendAction(ComponentAction.BuildDialog { activity ->
                        if (activity == null) {
                            return@BuildDialog null
                        }
                        return@BuildDialog createDialog(activity)
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