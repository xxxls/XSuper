package com.xxxxls.xsuper.callback

/**
 * 带loading 功能的回调
 * @author Max
 * @date 2019-12-04.
 */
interface LoadingCallBack<T> : XSuperCallBack<T> {

    /**
     * 是否展示loading
     */
    fun isShowLoading(): Boolean {
        return false
    }

}