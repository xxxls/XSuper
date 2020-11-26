package com.xxxxls.module_base.net.response

import com.xxxxls.xsuper.mvvm.XSuperFlow
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.AbstractFlow
import kotlinx.coroutines.flow.FlowCollector

/**
 * Flow - 基础响应
 * @author Max
 * @date 2020/11/26.
 */
@FlowPreview
class BaseResponseFlow<T>(block: suspend FlowCollector<BaseResponse<T>>.() -> Unit) :
    XSuperFlow<BaseResponse<T>>(block) {
}