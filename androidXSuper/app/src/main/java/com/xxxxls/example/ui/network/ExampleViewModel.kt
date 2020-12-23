package com.xxxxls.example.ui.network

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.xxxxls.example.ui.network.data.bean.ArticleItemBean
import com.xxxxls.example.ui.network.data.repository.ExampleRepository
import com.xxxxls.module_base.util.ILog
import com.xxxxls.xsuper.model.ResultLiveData
import com.xxxxls.xsuper.viewmodel.XSuperViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

/**
 * 示例列表页 - VM
 * @author Max
 * @date 2020/11/25.
 */
class ExampleViewModel @ViewModelInject constructor(private val repository:ExampleRepository) :
    XSuperViewModel(), ILog {

    val listLiveData: ResultLiveData<List<ArticleItemBean>> =
        ResultLiveData<List<ArticleItemBean>>()

    /**
     * 获取列表数据
     */
    fun fetchListData() {
        viewModelScope.launch(context = Dispatchers.IO) {
            logD("fetchListData() thread:${Thread.currentThread().name} ,thread:${Thread.currentThread().hashCode()}")
            repository.getArticleList(1).onStart {
                logD("fetchListData.onStart")
            }.collectLatest {
                listLiveData.postValue(it)
                logD("collectLatest")
            }
        }
    }
}