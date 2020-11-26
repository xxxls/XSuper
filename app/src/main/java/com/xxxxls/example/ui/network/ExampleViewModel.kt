package com.xxxxls.example.ui.network

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.xxxxls.example.ui.network.data.bean.ArticleItemBean
import com.xxxxls.example.ui.network.data.repository.ExampleRepository
import com.xxxxls.logger.XLogger
import com.xxxxls.module_base.util.ILog
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

/**
 * 示例列表页 - VM
 * @author Max
 * @date 2020/11/25.
 */
class ExampleViewModel @ViewModelInject constructor(private val repository: ExampleRepository) :
    ViewModel(), ILog {

    val listLiveData: MutableLiveData<List<ArticleItemBean>> =
        MutableLiveData<List<ArticleItemBean>>()

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