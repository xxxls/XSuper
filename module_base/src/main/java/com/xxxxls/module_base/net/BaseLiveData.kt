package com.xxxxls.module_base.net

import com.xxxxls.xsuper.net.XSuperLiveData
import com.xxxxls.xsuper.net.XSuperLoadingCallBack

/**
 * liveData 带 loading功能
 * @author Max
 * @date 2019-12-04.
 */
class BaseLiveData<T>(
    //是否展示loading
    private var isShowLoading: Boolean = true
) : XSuperLiveData<T>(), XSuperLoadingCallBack<T> {

    override fun isShowLoading(): Boolean {
        return isShowLoading
    }

    fun setShowLoading(isShowLoading: Boolean) {
        this.isShowLoading = isShowLoading
    }
}