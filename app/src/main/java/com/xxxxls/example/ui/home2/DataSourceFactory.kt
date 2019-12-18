package com.xxxxls.example.ui.home2

import androidx.paging.DataSource
import com.xxxxls.adapter.paging.IPositionalDataSource
import com.xxxxls.adapter.paging.XSuperPositionalDataSource
import com.xxxxls.example.bean.ArticleBean

/**
 *
 * @author Max
 * @date 2019-12-17.
 */
class DataSourceFactory(val positionalDataSource: IPositionalDataSource<ArticleBean>) :
    DataSource.Factory<Int, ArticleBean>() {
    override fun create(): DataSource<Int, ArticleBean> {
        return XSuperPositionalDataSource<ArticleBean>(positionalDataSource)
    }

}