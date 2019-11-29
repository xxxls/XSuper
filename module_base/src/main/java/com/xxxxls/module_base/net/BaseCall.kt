package com.xxxxls.module_base.net

import com.xxxxls.module_base.net.response.BaseResponse
import com.xxxxls.xsuper.net.call.XSuperCall

/**
 * 基础 - Call
 * @author Max
 * @date 2019-11-29.
 */
interface BaseCall<T> : XSuperCall<BaseResponse<T>>