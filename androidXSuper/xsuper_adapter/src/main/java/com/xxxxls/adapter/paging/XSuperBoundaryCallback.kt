package com.xxxxls.adapter.paging

import androidx.paging.PagedList

/**
 * BoundaryCallback
 * @author Max
 * @date 2019-12-23.
 */
class XSuperBoundaryCallback<T>(private val statusListener: OnListStatusListener?) :
    PagedList.BoundaryCallback<T>() {

    override fun onZeroItemsLoaded() {
        super.onZeroItemsLoaded()
        statusListener?.onListStatusChange(XSuperListStatus.Empty())
    }

    override fun onItemAtEndLoaded(itemAtEnd: T) {
        super.onItemAtEndLoaded(itemAtEnd)
        statusListener?.onListStatusChange(XSuperListStatus.LoadMoreEnd())
    }

    override fun onItemAtFrontLoaded(itemAtFront: T) {
        super.onItemAtFrontLoaded(itemAtFront)
        statusListener?.onListStatusChange(XSuperListStatus.FrontEnd())
    }

}