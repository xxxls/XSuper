package com.xxxxls.example.ui.home2

import android.graphics.Color
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import com.xxxxls.adapter.decoration.CommonItemDecoration
import com.xxxxls.example.R
import com.xxxxls.example.adapter.HomeArticleAdapter
import com.xxxxls.example.adapter.HomeArticleAdapter2
import com.xxxxls.example.bean.ArticleBean
import com.xxxxls.module_base.base.BaseFragment
import com.xxxxls.module_base.net.response.BaseResponse
import com.xxxxls.xsuper.net.viewmodel.ViewModelFactory
import com.xxxxls.utils.px
import com.xxxxls.utils.toast
import kotlinx.android.synthetic.main.fragment_article_list.*

/**
 * 首页文章
 * @author Max
 * @date 2019-12-07.
 */
class HomeArticleListFragment2 : BaseFragment() {

    private val mAdapter: HomeArticleAdapter2 by lazy {
        HomeArticleAdapter2()
    }

    private val mViewModel by ViewModelFactory(
        HomeArticleListViewModel2::class.java
    )

    override fun getLayoutResId(): Int? {
        return R.layout.fragment_article_list
    }

    override fun onInitialize() {
        super.onInitialize()
        initView()
        initEvent()
//        refreshLayout.setEnableLoadMore(false)
//        refreshLayout.autoRefresh()
    }

    override fun onInitObserve() {
        super.onInitObserve()
        mViewModel.listLiveData.observe(this,
            Observer<PagedList<ArticleBean>> { t -> mAdapter.submitList(t) })

        mViewModel.listStatusLiveData.observe(this, success = {
            if(it){
                refreshLayout.finishRefresh()
            }else{
                refreshLayout.finishLoadMore()
            }
        }, error = {
            refreshLayout.finishRefresh(false)
            refreshLayout.finishLoadMore(false)
        })
    }

    private fun initView() {
        recyclerView.adapter = mAdapter
        recyclerView.layoutManager = LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)
    }

    private fun initEvent() {
        refreshLayout.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onLoadMore(refreshLayout: RefreshLayout) {
                mViewModel.loadMore()
            }

            override fun onRefresh(refreshLayout: RefreshLayout) {
                mViewModel.refresh()
            }
        })
    }
}