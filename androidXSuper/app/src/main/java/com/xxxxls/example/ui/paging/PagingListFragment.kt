package com.xxxxls.example.ui.paging

import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener
import com.xxxxls.example.R
import com.xxxxls.example.ui.paging.adapter.PagingListAdapter
import com.xxxxls.module_base.component.BaseFragment
import com.xxxxls.module_base.constants.HomePaths
import com.xxxxls.module_base.util.status
import com.xxxxls.xsuper.viewmodel.superViewModels
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_paging_list.*

/**
 * Paging List
 * @author Max
 * @date 2019-12-07.
 */
@AndroidEntryPoint
@Route(path = HomePaths.HOME_FRAGMENT_PAGING_LIST)
class PagingListFragment : BaseFragment() {

    private val mAdapter: PagingListAdapter by lazy {
        PagingListAdapter()
    }

    //类型
    @JvmField
    @Autowired(name = HomePaths.KEY_HOME_FRAGMENT_PAGING_LIST_TYPE)
    var type: Int = 0

    private val mViewModel: PagingViewModel by superViewModels()

    override fun getLayoutResId(): Int? {
        return R.layout.fragment_paging_list
    }

    override fun onInitialize() {
        super.onInitialize()
        initView()
        initEvent()
    }

    override fun onInitObserve() {
        super.onInitObserve()

        mViewModel.listLiveData.observe(this, Observer { data ->
            mAdapter.submitData(lifecycle, data)
        })

//        mViewModel.listStatusLiveData.observe(this,
//            Observer<XSuperListStatus> {
//                when (it) {
//                    is XSuperListStatus.Initialize -> {
//                        refreshLayout.autoRefreshAnimationOnly()
//                    }
//                    is XSuperListStatus.InitializeSuccess -> {
//                        refreshLayout.finishRefresh()
//                        refreshLayout.showContent(300)
//                    }
//                    is XSuperListStatus.InitializeError -> {
//                        refreshLayout.finishRefresh(false)
//                        refreshLayout.showError(300)
//                    }
//                    is XSuperListStatus.Empty -> {
//                        refreshLayout.finishRefresh(false)
//                        refreshLayout.finishLoadMore(false)
//                        refreshLayout.showEmpty(300)
//                    }
//                    is XSuperListStatus.LoadMoreIn -> {
//
//                    }
//                    is XSuperListStatus.LoadMoreSuccess -> {
//                        refreshLayout.finishLoadMore()
//                    }
//                    is XSuperListStatus.LoadMoreError -> {
//                        refreshLayout.finishLoadMore(false)
//                    }
//                    is XSuperListStatus.LoadMoreEnd -> {
//                        refreshLayout.finishLoadMore()
//                    }
//                    is XSuperListStatus.FrontLoadMoreIn -> {
//
//                    }
//                    is XSuperListStatus.FrontLoadMoreSuccess -> {
//                        refreshLayout.finishRefresh()
//                        refreshLayout.finishLoadMore()
//                    }
//                    is XSuperListStatus.FrontLoadMoreError -> {
//                        refreshLayout.finishRefresh(false)
//                        refreshLayout.finishLoadMore(false)
//                    }
//                    is XSuperListStatus.FrontEnd -> {
//                        refreshLayout.finishRefresh(false)
//                        refreshLayout.finishLoadMore(false)
//                    }
//                }
//            })
    }
//
    private fun initView() {
        recyclerView.adapter = mAdapter
        recyclerView.layoutManager = LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)

        refreshLayout.status {
//            mViewModel.refresh()
        }
    }
//
    private fun initEvent() {
        refreshLayout.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onLoadMore(refreshLayout: RefreshLayout) {
                mAdapter.retry()
            }

            override fun onRefresh(refreshLayout: RefreshLayout) {
                mAdapter.refresh()
            }
        })

//        mAdapter.setOnItemChildClickListener(object : OnItemChildClickListener {
//            override fun onItemChildClick(view: View, position: Int) {
//                when (view.id) {
//                    R.id.tv_add -> {
//                        L.e("onItemChildClick - add")
//                        val id = position + 10
//                        mAdapter.addData(
//                            arrayListOf(
//                                TestPagingBean(
//                                    position + 10,
//                                    title = "add#item#$id",
//                                    content = "content:$id"
//                                )
//                            )
//                            , position
//                        )
//                    }
//                    R.id.tv_del -> {
//                        L.e("onItemChildClick - del")
//                        mAdapter.remove(position)
//                    }
//                }
//            }
//        })
    }
}