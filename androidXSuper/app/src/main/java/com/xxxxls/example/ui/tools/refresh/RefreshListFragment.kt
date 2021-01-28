package com.xxxxls.example.ui.tools.refresh

import com.alibaba.android.arouter.facade.annotation.Route
import com.xxxxls.example.R
import com.xxxxls.example.bean.TestPagingBean
import com.xxxxls.module_base.adapter.IListAdapter
import com.xxxxls.module_base.component.BaseFragment
import com.xxxxls.module_base.constants.HomePaths
import com.xxxxls.module_base.refresh.SmartListRefreshHelper
import com.xxxxls.module_base.refresh.buildSmartRefresh
import com.xxxxls.module_base.widget.refresh.IRefreshLayout
import com.xxxxls.xsuper.viewmodel.superViewModels
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_refresh_index.*

/**
 * 智能刷新组件
 * @author Max
 * @date 1/28/21.
 */
@AndroidEntryPoint
@Route(path = HomePaths.HOME_FRAGMENT_LIST_REFRESH_INDEX)
class RefreshListFragment : BaseFragment(), SmartListRefreshHelper.IView<TestPagingBean> {

    private val adapter: RefreshListAdapter by lazy {
        RefreshListAdapter()
    }

    private val viewModel: RefreshListViewModel by superViewModels()

    override fun getLayoutResId(): Int {
        return R.layout.fragment_refresh_index
    }

    override fun onInitialize() {
        super.onInitialize()
        recyclerView.adapter = adapter

        // 通过这个方法构建出智能刷新器
        buildSmartRefresh(viewModel).apply {
            // 开启异常toast提示
            enableToast()
            // 刷新并展示加载状态
            refreshAndShowLoading()
        }
    }

    override fun getAdapter(): IListAdapter<TestPagingBean> {
        return adapter
    }

    override fun getRefreshLayout(): IRefreshLayout {
        return refreshLayout
    }
}