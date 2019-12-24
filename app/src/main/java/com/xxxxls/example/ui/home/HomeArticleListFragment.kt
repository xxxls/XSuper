package com.xxxxls.example.ui.home

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import com.xxxxls.example.R
import com.xxxxls.example.adapter.HomeArticleAdapter
import com.xxxxls.module_base.base.BaseFragment
import com.xxxxls.xsuper.net.viewmodel.ViewModelFactory
import com.xxxxls.utils.toast
import kotlinx.android.synthetic.main.fragment_paging_list.*

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
        return R.layout.fragment_paging_list
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
            com.xxxxls.utils.L.e("list-success:$it")
            mAdapter.submitList(it.datas)
            refreshLayout.finishRefresh()
            refreshLayout.finishLoadMore()
        }, error = {
            toast(it.displayMessage)
            refreshLayout.state
            refreshLayout.finishRefresh(false)
            refreshLayout.finishLoadMore(false)
            com.xxxxls.utils.L.e("list-error:$it")
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