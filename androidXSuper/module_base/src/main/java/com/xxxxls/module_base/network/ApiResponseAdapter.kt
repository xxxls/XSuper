package com.xxxxls.module_base.network

import com.xxxxls.module_base.util.ILog
import com.xxxxls.xsuper.adapter.DefaultResponseAdapter
import com.xxxxls.xsuper.exceptions.ApiException
import com.xxxxls.xsuper.exceptions.CodeException
import com.xxxxls.xsuper.exceptions.XSuperException
import com.xxxxls.xsuper.model.*
import com.xxxxls.xsuper.adapter.ResponseAdapter
import java.lang.NullPointerException

/**
 * 接口响应适配器 （自定义）
 * @author Max
 * @date 2020/11/27.
 */
class ApiResponseAdapter private constructor() : DefaultResponseAdapter(), ILog {

    companion object {

        val instance: ApiResponseAdapter by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            ApiResponseAdapter()
        }
    }

}