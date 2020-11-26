package com.xxxxls.example.ui.network.data.local

import com.xxxxls.example.ui.network.data.bean.ArticleItemBean

/**
 * 示例 - 数据库
 * @author Max
 * @date 2020/11/25.
 */
interface ExampleDao {

    /**
     * 获取列表
     */
    fun getList(page: Int): List<ArticleItemBean>?
}