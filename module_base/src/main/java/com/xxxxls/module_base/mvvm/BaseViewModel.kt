package com.xxxxls.module_base.mvvm

import com.xxxxls.module_base.util.ILog
import com.xxxxls.xsuper.adapter.ResponseAdapter
import com.xxxxls.xsuper.viewmodel.XSuperViewModel
import javax.inject.Inject

/**
 * 项目基础 VM
 * @author Max
 * @date 2019-11-29.
 */
open class BaseViewModel : XSuperViewModel(), ILog {

    @Inject
    var adapter: ResponseAdapter? = null

}