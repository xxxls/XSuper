package com.xxxxls.example.home

import android.graphics.Color
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import com.xxxxls.adapter.CommonItemDecoration
import com.xxxxls.example.R
import com.xxxxls.example.adapter.HomeArticleAdapter
import com.xxxxls.module_base.base.BaseFragment
import com.xxxxls.xsuper.net.viewmodel.ViewModelFactory
import com.xxxxls.xsuper.util.L
import com.xxxxls.xsuper.util.dp
import com.xxxxls.xsuper.util.px
import com.xxxxls.xsuper.util.toast
import kotlinx.android.synthetic.main.fragment_article_list.*

/**
 * 首页文章
 * @author Max
 * @date 2019-12-07.
 */
class HomeArticleListFragment : BaseFragment() {

    private val mAdapter: HomeArticleAdapter by lazy {
        HomeArticleAdapter()
    }

    private val mViewModel by ViewModelFactory(
        HomeArticleListViewModel::class.java
    )

    override fun getLayoutResId(): Int? {
        return R.layout.fragment_article_list
    }

    override fun onInitialize() {
        super.onInitialize()
        initView()
        initEvent()
        refreshLayout.autoRefresh()
    }

    override fun onInitObserve() {
        super.onInitObserve()
        mViewModel.listLiveData.observe(success = {
            L.e("list-success:$it")
            mAdapter.submitList(it.datas)
            refreshLayout.finishRefresh()
            refreshLayout.finishLoadMore()
        }, error = {
            toast(it.displayMessage)
            refreshLayout.state
            refreshLayout.finishRefresh(false)
            refreshLayout.finishLoadMore(false)
            L.e("list-error:$it")
        })
    }

    private fun initView() {
        recyclerView.adapter = mAdapter
        recyclerView.layoutManager = LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)
        recyclerView.addItemDecoration(
            CommonItemDecoration.builder().widthAndHeight(10.px()).color(Color.TRANSPARENT).build()
        )
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