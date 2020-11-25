package com.xxxxls.example.ui.network

import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.alibaba.android.arouter.facade.annotation.Route
import com.xxxxls.example.bean.SimpleItemBean
import com.xxxxls.example.common.BaseListFragment
import com.xxxxls.module_base.constants.HomePaths
import com.xxxxls.titlebar.setTitleBarLeftText
import dagger.hilt.android.AndroidEntryPoint

/**
 * 网络示例
 * @author Max
 * @date 2020/11/25.
 */
@AndroidEntryPoint
@Route(path = HomePaths.HOME_FRAGMENT_NETWORK_INDEX)
class NetworkIndexFragment : BaseListFragment() {

    private val viewModel: ExampleViewModel by viewModels()

    override fun onInitObserve() {
        super.onInitObserve()
        viewModel.listLiveData.observe(this, Observer {
            logE("getListData() observe size:${it.size}")
        })
    }

    override fun onInitialize() {
        super.onInitialize()
        setTitleBarLeftText("返回") {
            activity?.onBackPressed()
        }
    }

    override fun getTitle(): String {
        return "Network"
    }

    override fun getItems(): Array<SimpleItemBean> {
        return arrayOf(
            SimpleItemBean("get"),
            SimpleItemBean("post")
        )
    }

    override fun onItemClick(index: Int, item: SimpleItemBean) {
        when (index) {
            0 -> {
                viewModel.fetchListData()
            }
            1 -> {
                viewModel.fetchListData()
            }
        }
    }
}