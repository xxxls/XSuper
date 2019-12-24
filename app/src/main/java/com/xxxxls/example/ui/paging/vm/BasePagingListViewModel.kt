package com.xxxxls.example.ui.paging.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.xxxxls.adapter.paging.XSuperListStatus
import com.xxxxls.adapter.paging.XSuperPaging
import com.xxxxls.example.bean.TestPagingBean
import com.xxxxls.example.net.HomeApis
import com.xxxxls.module_base.net.FastApiViewModel
import com.xxxxls.module_base.net.response.ListResponse
import com.xxxxls.utils.L

/**
 * BasePagingListViewModel
 * @author Max
 * @date 2019-12-24.
 */
abstract class BasePagingListViewModel : FastApiViewModel<HomeApis>(HomeApis::class.java) {

    val listLiveData: LiveData<PagedList<TestPagingBean>> by lazy {
        getXSuperPaging().build()
    }

    val listStatusLiveData: MutableLiveData<XSuperListStatus> by lazy {
        MutableLiveData<XSuperListStatus>()
    }

    abstract fun getXSuperPaging(): XSuperPaging<*, TestPagingBean>

    /**
     * 刷新
     */
    fun refresh() {
        L.e("refresh() -> ")
        getXSuperPaging().refresh()
    }

    /**
     * 重试
     */
    fun retry() {
        L.e("retry() -> ")
        val isRetry = getXSuperPaging().retry()
        if (!isRetry) {
            L.e("retry() -> false")
            //调用重试失败，重新刷新下当前状态
            listStatusLiveData.postValue(getXSuperPaging().status)
        } else {
            L.e("retry() -> true")
        }
    }

    /**
     * 往下加载数据
     */
    protected fun testData(position: Int): ListResponse<TestPagingBean> {
        val list = ArrayList<TestPagingBean>()
        val startIndex = position + 1
        val endIndex = startIndex + 20

        for (index in startIndex until endIndex) {
            list.add(TestPagingBean(index, "author:$position", "item#$index"))
        }

        return ListResponse(position, list, 0, false, 5, list.size, 5 * 20)
    }

    /**
     * 往上加载数据
     */
    protected fun testDataBefore(position: Int): ListResponse<TestPagingBean> {
        val list = ArrayList<TestPagingBean>()
        val endIndex = position
        val startIndex = endIndex + (20 * -1) + 1

        for (index in startIndex until endIndex) {
            list.add(TestPagingBean(index, "author:$position", "item#$index"))
        }

        return ListResponse(position, list, 0, false, 5, list.size, 5 * 20)
    }
}