package com.xxxxls.xsuper.net

/**
 * 带loading 功能的回调
 * @author Max
 * @date 2019-12-04.
 */
interface XSuperLoadingCallBack<T> : XSuperCallBack<T> {

    /**
     * 是否展示loading
     */
    fun isShowLoading(): Boolean {
        return false
    }

}