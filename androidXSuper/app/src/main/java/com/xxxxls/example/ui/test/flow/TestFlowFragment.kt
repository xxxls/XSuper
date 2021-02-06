package com.xxxxls.example.ui.test.flow

import com.alibaba.android.arouter.facade.annotation.Route
import com.xxxxls.example.bean.SimpleItemBean
import com.xxxxls.example.common.BaseListFragment
import com.xxxxls.module_base.constants.HomePaths
import com.xxxxls.xsuper.viewmodel.superViewModels
import dagger.hilt.android.AndroidEntryPoint

/**
 * 测试Flow使用
 * @author Max
 * @date 1/28/21.
 */
@Route(path = HomePaths.HOME_FRAGMENT_FLOW_INDEX)
@AndroidEntryPoint
class TestFlowFragment : BaseListFragment() {

    private val viewModel: TestFlowViewModel by superViewModels()

    override fun onInitialize() {
        super.onInitialize()
    }

    override fun onInitObserve() {
        super.onInitObserve()
        viewModel.testLiveData.observe(success = {
            logE("testLiveData success it:$it")
        }, failure = {
            logE("testLiveData failure it:$it")
        })
    }

    override fun getTitle(): String {
        return "Flow"
    }

    override fun getItems(): Array<SimpleItemBean> {
        return arrayOf(
            SimpleItemBean("test1")
        )
    }

    override fun onItemClick(index: Int, item: SimpleItemBean) {
        when (index) {
            0 -> {
                viewModel.test1()
            }
        }
    }
}