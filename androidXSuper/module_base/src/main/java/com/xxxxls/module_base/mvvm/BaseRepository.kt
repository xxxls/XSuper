package com.xxxxls.module_base.mvvm

import com.xxxxls.module_base.network.ApiResponseAdapter
import com.xxxxls.module_base.network.SafeExceptionConverter
import com.xxxxls.module_base.util.ILog
import com.xxxxls.xsuper.adapter.ExceptionConverter
import com.xxxxls.xsuper.adapter.ResponseAdapter
import com.xxxxls.xsuper.repository.SuperRepository

/**
 * BaseRepository
 * @author Max
 * @date 2020/11/28.
 */
open class BaseRepository : SuperRepository(), ILog {

    override fun getResponseAdapter(): ResponseAdapter {
        return ApiResponseAdapter.instance
    }

    override fun getExceptionConverter(): ExceptionConverter {
        return SafeExceptionConverter.instance
    }
}