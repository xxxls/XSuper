package com.xxxxls.example.ui.paging.positional

import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import com.xxxxls.adapter.paging.XSuperListStatus
import com.xxxxls.example.R
import com.xxxxls.example.adapter.PagingListAdapter
import com.xxxxls.example.bean.TestPagingBean
import com.xxxxls.module_base.base.BaseFragment
import com.xxxxls.xsuper.net.viewmodel.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_paging_list.*

/**
 * Paging - Positional
 * @author Max
 * @date 2019-12-07.
 */
class PositionalFragment : BaseFragment() {

    private val mAdapter: PagingListAdapter by lazy {
        PagingListAdapter()
    }

    private val mViewModel by ViewModelFactory(
        PositionalViewModel::class.java
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
        mViewModel.listLiveData.observe(this,
            Observer<PagedList<TestPagingBean>> { t -> mAdapter.submitList(t) })

        mViewModel.listStatusLiveData.observe(this,
            Observer<XSuperListStatus> {
                when (it) {
                    is XSuperListStatus.Initialize -> {

                    }
                    is XSuperListStatus.InitializeSuccess -> {
                        refreshLayout.finishRefresh()
                    }
                    is XSuperListStatus.InitializeError -> {
                        refreshLayout.finishRefresh(false)
                    }
                    is XSuperListStatus.Empty -> {
                        refreshLayout.finishRefresh(false)
                        refreshLayout.finishLoadMore(false)
                    }
                    is XSuperListStatus.LoadMoreIn -> {

                    }
                    is XSuperListStatus.LoadMoreSuccess -> {
                        refreshLayout.finishLoadMore()
                    }
                    is XSuperListStatus.LoadMoreError -> {
                        refreshLayout.finishLoadMore(false)
                    }
                    is XSuperListStatus.LoadMoreEnd -> {
                        refreshLayout.finishLoadMore()
                    }
                    is XSuperListStatus.FrontLoadMoreIn -> {

                    }
                    is XSuperListStatus.FrontLoadMoreSuccess -> {
                        refreshLayout.finishRefresh()
                        refreshLayout.finishLoadMore()
                    }
                    is XSuperListStatus.FrontLoadMoreError -> {
                        refreshLayout.finishRefresh(false)
                        refreshLayout.finishLoadMore(false)
                    }
                    is XSuperListStatus.FrontEnd -> {
                        refreshLayout.finishRefresh(false)
                        refreshLayout.finishLoadMore(false)
                    }
                }
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