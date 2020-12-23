package com.xxxxls.example.ui.network.data.repository

import com.xxxxls.example.ui.network.data.local.ExampleDao
import com.xxxxls.example.ui.network.data.bean.ArticleItemBean
import com.xxxxls.example.ui.network.data.api.PagingApi
import com.xxxxls.module_base.util.ILog
import com.xxxxls.xsuper.model.XSuperResult
import com.xxxxls.xsuper.model.toSuccessResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

/**
 * 示例 - Repository
 * @author Max
 * @date 2020/11/25.
 */
class ExampleRepository @Inject constructor(
    private val exampleApi: PagingApi,
    private val exampleDao: ExampleDao
) : ILog {

    /**
     * 获取文章列表
     */
    suspend fun getArticleList(page: Int): Flow<XSuperResult<List<ArticleItemBean>>> {
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
                emit(localList!!.toSuccessResult())
            }
        }.catch {
            // 异常
            logE("getArticleList() hashCode:${this.hashCode()} catch :${it.localizedMessage}")
            emit(ArrayList<ArticleItemBean>().toSuccessResult())
        }.flowOn(Dispatchers.IO)
    }
}