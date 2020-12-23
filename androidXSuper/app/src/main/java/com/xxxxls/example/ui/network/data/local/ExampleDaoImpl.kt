package com.xxxxls.example.ui.network.data.local

import com.xxxxls.example.ui.network.data.bean.ArticleItemBean
import com.xxxxls.module_base.util.ILog

/**
 * (这里临时临时模拟本地数据库)
 * @author Max
 * @date 2020/11/26.
 */
class ExampleDaoImpl : ExampleDao, ILog {
    override fun getList(page: Int): List<ArticleItemBean>? {
        logD("getList() hashCode:${hashCode()} ,page:$page")
        logD("getList() thread:${Thread.currentThread().name}  ,thread:${Thread.currentThread().hashCode()}")
        return emptyList()
    }
}