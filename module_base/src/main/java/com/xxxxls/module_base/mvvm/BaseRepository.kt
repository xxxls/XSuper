package com.xxxxls.module_base.mvvm

import com.xxxxls.module_base.network.ApiResponseAdapter
import com.xxxxls.xsuper.adapter.ResponseAdapter
import com.xxxxls.xsuper.repository.XSuperRepository

/**
 * BaseRepository
 * @author Max
 * @date 2020/11/28.
 */
open class BaseRepository : XSuperRepository() {

    override fun getResponseAdapter(): ResponseAdapter {
        return ApiResponseAdapter.instance
    }
}