package com.xxxxls.module_base.mvvm

import com.xxxxls.xsuper.adapter.ResponseAdapter
import com.xxxxls.xsuper.repository.XSuperRepository
import javax.inject.Inject

/**
 * BaseRepository
 * @author Max
 * @date 2020/11/28.
 */
open class BaseRepository : XSuperRepository() {

    @Inject
    var adapter: ResponseAdapter? = null

    override fun getResponseAdapter(): ResponseAdapter {
        adapter?.let {
            return it
        } ?: let {
            return super.getResponseAdapter()
        }
    }

}