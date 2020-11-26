package com.xxxxls.module_base.net.response

import com.xxxxls.xsuper.mvvm.XSuperFlow
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.AbstractFlow
import kotlinx.coroutines.flow.FlowCollector

/**
 * Flow - 基础响应 - 列表
 * @author Max
 * @date 2020/11/26.
 */
@FlowPreview
class BaseListResponseFlow<T>(block: suspend FlowCollector<BaseListResponse<T>>.() -> Unit) :
    XSuperFlow<BaseListResponse<T>>(block) {
}