package com.xxxxls.module_base.net

import com.xxxxls.xsuper.model.XSuperLiveData
import com.xxxxls.xsuper.callback.LoadingCallBack

/**
 * liveData 带 loading功能
 * @author Max
 * @date 2019-12-04.
 */
class BaseLiveData<T>(
    //是否展示loading
    private var isShowLoading: Boolean = true
) : XSuperLiveData<T>(), LoadingCallBack<T> {

    override fun isShowLoading(): Boolean {
        return isShowLoading
    }

    fun setShowLoading(isShowLoading: Boolean) {
        this.isShowLoading = isShowLoading
    }
}