package com.xxxxls.example.ui.paging.data.repository

import com.xxxxls.example.ui.paging.data.api.PagingApi
import com.xxxxls.example.ui.paging.data.bean.PagingItemBean
import com.xxxxls.module_base.util.ILog
import com.xxxxls.xsuper.model.XSuperResult
import kotlinx.coroutines.flow.*
import javax.inject.Inject

/**
 * 分页 - Repository
 * @author Max
 * @date 2020/12/23.
 */
class PagingRepository @Inject constructor(
    private val exampleApi: PagingApi
) : ILog {

    /**
     * 获取文章列表
     */
    suspend fun getArticleList(page: Int): Flow<XSuperResult<List<PagingItemBean>>> {
        return null!!
    }
}