package com.xxxxls.adapter.paging


/**
 * IListStatus状态更新
 * @author Max
 * @date 2019-12-20.
 */
interface OnListStatusListener {

    /**
     * 列表状态改变
     * @param status 状态
     */
    fun onListStatusChange(status: XSuperListStatus)
}