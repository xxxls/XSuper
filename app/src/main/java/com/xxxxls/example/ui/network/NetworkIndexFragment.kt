package com.xxxxls.example.ui.network

import com.alibaba.android.arouter.facade.annotation.Route
import com.xxxxls.example.bean.SimpleItemBean
import com.xxxxls.example.common.BaseListFragment
import com.xxxxls.module_base.constants.HomePaths
import com.xxxxls.module_user.ui.login.LoginViewModel
import com.xxxxls.titlebar.setTitleBarLeftText
import com.xxxxls.xsuper.viewmodel.viewModelsFactory
import com.xxxxls.xsuper.viewmodel.xsuperViewModels
import dagger.hilt.android.AndroidEntryPoint

/**
 * 网络示例
 * @author Max
 * @date 2020/11/25.
 */
@Route(path = HomePaths.HOME_FRAGMENT_NETWORK_INDEX)
@AndroidEntryPoint
class NetworkIndexFragment : BaseListFragment() {

    private val viewModel: ExampleViewModel by xsuperViewModels()

    private val viewModel2 : LoginViewModel by viewModelsFactory()

    override fun onInitObserve() {
        super.onInitObserve()
        viewModel.listLiveData.observe(this,
            success = {
                logE("listLiveData success list:${it!!.size}")
            }, failure = {
                logE("listLiveData failure error:${it.displayMessage}")
            })

        viewModel2.loginLiveData.observe(this,success = {
            logE("loginLiveData success list:${it}")
        },failure = {
            logE("loginLiveData failure error:${it.displayMessage}")
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
                viewModel2.login("Max","12212")
            }
            1 -> {
                viewModel.fetchListData()
            }
        }
    }
}