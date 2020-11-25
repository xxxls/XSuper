package com.xxxxls.example.ui.network

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.xxxxls.example.ui.network.data.bean.ArticleItemBean
import com.xxxxls.example.ui.network.data.repository.ExampleRepository
import com.xxxxls.logger.XLogger
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

/**
 * 示例列表页 - VM
 * @author Max
 * @date 2020/11/25.
 */
class ExampleViewModel @ViewModelInject constructor(private val repository: ExampleRepository) :
    ViewModel() {

    val listLiveData: MutableLiveData<List<ArticleItemBean>> =
        MutableLiveData<List<ArticleItemBean>>()

    /**
     * 获取列表数据
     */
    fun fetchListData() {
        viewModelScope.launch {
            repository.getArticleList().onStart {
                XLogger.e("fetchListData.onStart", "ExampleViewModel")
            }.collectLatest {
                listLiveData.postValue(it)
            }
        }
    }

}