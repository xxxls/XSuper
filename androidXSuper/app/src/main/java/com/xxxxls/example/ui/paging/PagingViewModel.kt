package com.xxxxls.example.ui.paging

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.paging.PagingData
import com.xxxxls.example.ui.paging.data.bean.PagingItemBean
import com.xxxxls.example.ui.paging.data.repository.PagingRepository
import com.xxxxls.module_base.mvvm.BaseViewModel

/**
 * @author Max
 * @date 12/23/20.
 */
class PagingViewModel @ViewModelInject constructor(private val repository: PagingRepository) :
    BaseViewModel() {

    val listLiveData: LiveData<PagingData<PagingItemBean>> by lazy {
        repository.getArticleList().asLiveData()
    }

}