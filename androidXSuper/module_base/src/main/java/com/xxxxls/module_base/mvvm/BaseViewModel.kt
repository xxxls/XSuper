package com.xxxxls.module_base.mvvm

import com.xxxxls.module_base.network.ApiResultAnalyzer
import com.xxxxls.module_base.util.ILog
import com.xxxxls.xsuper.adapter.ResultAnalyzer
import com.xxxxls.xsuper.viewmodel.SuperViewModel

/**
 * 项目基础 VM
 * @author Max
 * @date 2019-11-29.
 */
open class BaseViewModel : SuperViewModel(), ILog {

    override fun getResultAnalyzer(): ResultAnalyzer {
        return ApiResultAnalyzer.instance
    }
}