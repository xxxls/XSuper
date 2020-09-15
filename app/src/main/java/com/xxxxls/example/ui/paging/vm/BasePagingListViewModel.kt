package com.xxxxls.example.ui.paging.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.xxxxls.adapter.paging.XSuperListStatus
import com.xxxxls.adapter.paging.XSuperPaging
import com.xxxxls.example.bean.TestPagingBean
import com.xxxxls.example.net.HomeRepository
import com.xxxxls.module_base.net.BaseViewModel
import com.xxxxls.utils.L

/**
 * BasePagingListViewModel
 * @author Max
 * @date 2019-12-24.
 */
abstract class BasePagingListViewModel : BaseViewModel() {

    protected val mHomeRepository = createRepository(HomeRepository::class.java)

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
        val result = getXSuperPaging().refresh()
        L.e("refresh() -> $result")
    }

    /**
     * 重试
     */
    fun retry() {
        val isRetry = getXSuperPaging().retry()
        if (!isRetry) {
            L.e("retry() -> false")
            //调用重试失败，重新刷新下当前状态
            listStatusLiveData.postValue(getXSuperPaging().status)
        } else {
            L.e("retry() -> true")
        }
    }
}