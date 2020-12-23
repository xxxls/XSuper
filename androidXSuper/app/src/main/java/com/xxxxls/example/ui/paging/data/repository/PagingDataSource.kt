package com.xxxxls.example.ui.paging.data.repository

import androidx.paging.PagingSource
import com.xxxxls.example.ui.paging.data.api.PagingApi
import com.xxxxls.example.ui.paging.data.bean.PagingItemBean
import javax.inject.Inject

/**
 * 分页数据源
 * @author Max
 * @date 12/23/20.
 */
class PagingDataSource @Inject constructor(
    private val pagingApi: PagingApi
) : PagingSource<Int, PagingItemBean>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PagingItemBean> {
        return try {
            // key 相当于 id
            val key = params.key ?: 0
            // 获取网络数据
            val items = pagingApi.getArticleList().getListNoNull()
            // 请求失败或者出现异常，会跳转到 case 语句返回 LoadResult.Error(e)
            // 请求成功，构造一个 LoadResult.Page 返回
            LoadResult.Page(
                data = items, // 返回获取到的数据
                prevKey = null, // 上一页，设置为空就没有上一页的效果，这需要注意的是，如果是第一页需要返回 null，否则会出现多次请求
                nextKey = null// 下一页，设置为空就没有加载更多效果，需要注意的是，如果是最后一页返回 null
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }
}