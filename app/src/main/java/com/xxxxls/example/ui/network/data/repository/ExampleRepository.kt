package com.xxxxls.example.ui.network.data.repository

import com.xxxxls.example.ui.network.data.local.ExampleDao
import com.xxxxls.example.ui.network.data.bean.ArticleItemBean
import com.xxxxls.example.ui.network.data.api.ExampleApi
import com.xxxxls.module_base.net.response.BaseListResponse
import com.xxxxls.module_base.util.ILog
import com.xxxxls.xsuper.net.XSuperResult
import com.xxxxls.xsuper.net.toSuccessResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Singleton

/**
 * 示例 - Repository
 * @author Max
 * @date 2020/11/25.
 */
class ExampleRepository @Inject constructor(
    private val exampleApi: ExampleApi,
    private val exampleDao: ExampleDao
) : ILog {

    /**
     * 获取文章列表
     */
    fun getArticleList(page: Int): Flow<XSuperResult<List<ArticleItemBean>>> {
        logD("getArticleList() hashCode:${hashCode()} ,page:$page")
        return flow {
            logD(
                "getArticleList() thread:${Thread.currentThread().name}  ,thread:${Thread.currentThread()
                    .hashCode()}"
            )
            // 本地数据查询
            val localList = exampleDao.getList(page)
            if (localList.isNullOrEmpty()) {
                // 网络获取
                val list = exampleApi.getArticleList()
                emit(list.getListNoNull().toSuccessResult())
            } else {
                emit(localList!!)
            }
        }.catch {
            // 异常
            logE("getArticleList() hashCode:${this.hashCode()} catch :${it.localizedMessage}")
            emit(emptyList().toSu)
        }.onCompletion {
            // 完成
            logE("getArticleList() hashCode:${this.hashCode()} onCompletion")
        }.flowOn(Dispatchers.IO)
    }
}