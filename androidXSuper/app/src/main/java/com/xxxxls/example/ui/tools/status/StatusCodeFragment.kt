package com.xxxxls.example.ui.tools.status

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xxxxls.example.R
import com.xxxxls.module_base.adapter.SimpleAdapter
import com.xxxxls.module_base.component.BaseFragment
import com.xxxxls.status.*
import com.xxxxls.utils.L
import com.xxxxls.utils.date.DateUtils
import com.xxxxls.utils.date.toDate
import com.xxxxls.utils.ktx.singleClick
import com.xxxxls.utils.ktx.toast
import kotlinx.android.synthetic.main.fragment_status_xml.*
import kotlinx.android.synthetic.main.layout_status_operation.*

/**
 * 多状态视图 CODE
 * @author Max
 * @date 2019-12-16.
 */
class StatusCodeFragment : BaseFragment() {

    val adapter = SimpleAdapter()

    var statusView: SuperStatusView? = null

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
            onRetry = { status: Status ->
                L.e("状态重试：status -> $status")
                loading()
            })

        // 添加自定义状态
        recyclerView.getStatusView()?.addStatus(CustomStatus, R.layout.layout_status_custom)

        //设置某个状态下的子view文本
        recyclerView.setStatusText(Status.NoNetwork, R.id.base_status_hint_content, "没有网络吗？")

        //设置某个状态下的子view点击事件
        recyclerView.setOnStatusChildViewClickListener(
            Status.Empty,
            R.id.base_status_hint_content
        ) {
            toast("点我了")
        }
    }

    private fun initEvent() {

        recyclerView.getStatusView()?.setOnStatusChangeListener(object : IStatusView.OnStatusChangeListener {
            override fun onChange(newStatus: Status, oldStatus: Status) {
                L.e("状态改变：$newStatus -#- $oldStatus")
            }
        })

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
            val time = 4000L
            toast("延时${time}毫秒再切换")
            recyclerView?.showError(time)
        }

        tv_no_work.singleClick {
            recyclerView?.showNoNetwork()
        }

        tv_custom.singleClick {
            val time = 2000L
            toast("延时${time}毫秒再切换")
            recyclerView?.switchStatus(CustomStatus, time)
        }
    }

    private fun loading() {
        val status = when ((0..3).random()) {
            0 -> {
                Status.Empty
            }
            1 -> {
                Status.Error
            }
            2 -> {
                Status.NoNetwork
            }
            else -> {
                Status.Content
            }
        }
        recyclerView?.switchStatus(status, 3000)
    }
}