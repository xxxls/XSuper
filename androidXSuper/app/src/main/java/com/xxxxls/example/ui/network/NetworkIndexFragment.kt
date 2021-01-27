package com.xxxxls.example.ui.network

import com.alibaba.android.arouter.facade.annotation.Route
import com.xxxxls.example.bean.SimpleItemBean
import com.xxxxls.example.common.BaseListFragment
import com.xxxxls.module_base.constants.HomePaths
import com.xxxxls.module_user.ui.login.LoginViewModel
import com.xxxxls.titlebar.setTitleBarLeftText
import com.xxxxls.utils.ktx.trueLet
import com.xxxxls.xsuper.viewmodel.xsuperViewModels
import com.xxxxls.xsuper.viewmodel.xsuperViewModelsF
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

    private val viewModel2 : LoginViewModel by xsuperViewModelsF()

    override fun onInitObserve() {
        super.onInitObserve()
        viewModel.listLiveData.observe(this,
            success = {
                logE("listLiveData success list:${it!!.size}")
            }, failure = {
                logE("listLiveData failure error:${it.message}")
            })

        viewModel2.loginLiveData.observe(this,success = {
            logE("loginLiveData success list:${it}")
        },failure = {
            logE("loginLiveData failure error:${it}")
        })

        viewModel2.userLiveData.observe(this,success = {
            logE("userLiveData success list:${it}")
        },failure = {
            logE("userLiveData failure error:${it}")
        })

    }

    override fun onInitialize() {
        super.onInitialize()
        setTitleBarLeftText("返回") {
            activity?.onBackPressed()
        }
        val result = true
        result.trueLet {

        }.elseLet {

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
//                viewModel.fetchListData()
                viewModel2.loginAndGetUserInfo2("xxxxls","xxxxls1234562")
            }
            1 -> {
                viewModel.fetchListData()
            }
        }
    }
}