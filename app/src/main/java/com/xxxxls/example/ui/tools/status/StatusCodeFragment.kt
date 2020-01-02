package com.xxxxls.example.ui.tools.status

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xxxxls.example.R
import com.xxxxls.module_base.adapter.SimpleAdapter
import com.xxxxls.module_base.base.BaseFragment
import com.xxxxls.status.*
import com.xxxxls.utils.L
import com.xxxxls.utils.singleClick
import com.xxxxls.utils.toast
import kotlinx.android.synthetic.main.fragment_status_xml.recyclerView
import kotlinx.android.synthetic.main.layout_status_operation.*

/**
 * 多状态视图 CODE
 * @author Max
 * @date 2019-12-16.
 */
class StatusCodeFragment : BaseFragment() {

    val adapter = SimpleAdapter()

    var statusView: XSuperStatusView? = null

    override fun getLayoutResId(): Int {
        return R.layout.fragment_status_code
    }

    override fun onInitialize() {
        super.onInitialize()
        initView()
        initEvent()
    }

    private fun initView() {
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        adapter.submitList(
            listOf(
                "Aba",
                "Brigitte",
                "Coral",
                "Dawn",
                "Ebony",
                "Farrah",
                "Galatea",
                "Hana",
                "Iolanthe",
                "Janna",
                "Kara",
                "Lamaara",
                "Madeline",
                "Nabila",
                "Oceana",
                "Pamela",
                "Qamar",
                "Rachel",
                "Saadiya",
                "Tallulah",
                "Ursula",
                "Vevina",
                "Wilma",
                "Xylona",
                "Yasmin",
                "Zea"
            )
        )

        //配置多状态
        recyclerView.statusConfig(
            loadingRes = R.layout.base_status_loading,
            errorRes = R.layout.base_status_error,
            emptyRes = R.layout.base_status_empty,
            noNetworkRes = R.layout.base_status_no_network,
            isAutoSwitchLoading = true,
            onRetry = { status: XStatus ->
                L.e("状态重试：status -> $status")
                loading()
            })

        //设置某个状态下的子view文本
        recyclerView.setStatusText(XStatus.NoNetwork, R.id.base_status_hint_content, "没有网络吗？")

        //设置某个状态下的子view点击事件
        recyclerView.setOnStatusChildViewClickListener(
            XStatus.Empty,
            R.id.base_status_hint_content
        ) {
            toast("点我了")
        }
    }

    private fun initEvent() {

        tv_content.singleClick {
            recyclerView?.showContent()
        }

        tv_loading.singleClick {
            recyclerView?.showLoading()
        }

        tv_empty.singleClick {
            recyclerView?.showEmpty()
        }

        tv_error.singleClick {
            recyclerView?.showError()
        }

        tv_no_work.singleClick {
            recyclerView?.showNoNetwork()
        }

    }

    private fun loading() {
        val status = when ((0..3).random()) {
            0 -> {
                XStatus.Empty
            }
            1 -> {
                XStatus.Error
            }
            2 -> {
                XStatus.NoNetwork
            }
            else -> {
                XStatus.Content
            }
        }
        recyclerView?.switchStatus(status, 3000)
    }
}