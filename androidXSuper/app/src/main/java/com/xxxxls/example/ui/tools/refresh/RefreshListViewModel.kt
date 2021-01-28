package com.xxxxls.example.ui.tools.refresh

import androidx.hilt.lifecycle.ViewModelInject
import com.xxxxls.example.bean.TestPagingBean
import com.xxxxls.example.data.HomeRepository
import com.xxxxls.module_base.mvvm.BaseViewModel
import com.xxxxls.module_base.network.response.IListResponse
import com.xxxxls.module_base.refresh.SmartListRefreshHelper
import com.xxxxls.xsuper.callback.SuperCallBack

/**
 * @author Max
 * @date 1/28/21.
 */
class RefreshListViewModel @ViewModelInject constructor(private val repository: HomeRepository) :
    BaseViewModel(), SmartListRefreshHelper.ISource<TestPagingBean> {

    override fun requestList(page: Int, callBack: SuperCallBack<IListResponse<TestPagingBean>>) {
        logE("requestList() page:$page")
        launchF(callBack, loading = null) {
            repository.getTestList(false, page, true)
        }
    }

}