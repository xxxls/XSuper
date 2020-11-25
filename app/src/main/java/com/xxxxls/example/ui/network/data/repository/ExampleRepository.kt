package com.xxxxls.example.ui.network.data.repository

import com.xxxxls.example.ui.network.data.local.ExampleDao
import com.xxxxls.example.ui.network.data.bean.ArticleItemBean
import com.xxxxls.example.ui.network.data.api.ExampleApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import javax.inject.Inject

/**
 * 示例 - Repository
 * @author Max
 * @date 2020/11/25.
 */
class ExampleRepository @Inject constructor(
    val exampleApi: ExampleApi,
    val exampleDao: ExampleDao
) {

    /**
     * 获取文章列表
     */
    fun getArticleList(): Flow<List<ArticleItemBean>> {
        return flow {
            val list = exampleApi.getArticleList()
            emit(list)
        }.catch {
            // 异常

        }.onCompletion {
            // 完成
        }
    }
}