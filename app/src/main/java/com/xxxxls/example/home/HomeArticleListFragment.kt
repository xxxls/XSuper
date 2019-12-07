package com.xxxxls.example.home

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xxxxls.example.R
import com.xxxxls.example.adapter.HomeArticleAdapter
import com.xxxxls.module_base.base.BaseFragment
import com.xxxxls.xsuper.net.viewmodel.ViewModelFactory
import com.xxxxls.xsuper.util.L
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
        recyclerView.adapter = mAdapter
        recyclerView.layoutManager = LinearLayoutManager(this.context, RecyclerView.VERTICAL, false)
        mViewModel.refresh()
    }

    override fun onInitObserve() {
        super.onInitObserve()
        mViewModel.listLiveData.observe(success = {
            L.e("list-success:$it")
        }, error = {
            L.e("list-error:$it")
        })
    }
}