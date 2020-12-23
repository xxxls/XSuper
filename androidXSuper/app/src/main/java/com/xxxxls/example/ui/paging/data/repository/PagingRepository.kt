package com.xxxxls.example.ui.paging.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
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
    private val pagingApi: PagingApi,
    private val pagingConfig: PagingConfig
) : ILog {

    /**
     * 获取文章列表
     */
    fun getArticleList(): Flow<PagingData<PagingItemBean>> {
        return Pager(pagingConfig) {
            // 加载数据库的数据
            PagingDataSource(pagingApi)
        }.flow
    }
}