package com.xxxxls.example.ui.tools.status

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xxxxls.example.R
import com.xxxxls.module_base.adapter.SimpleAdapter
import com.xxxxls.module_base.component.BaseFragment
import com.xxxxls.status.IStatusView
import com.xxxxls.status.Status
import com.xxxxls.status.getStatusView
import com.xxxxls.status.switchStatus
import com.xxxxls.utils.L
import com.xxxxls.utils.ktx.singleClick
import kotlinx.android.synthetic.main.fragment_status_xml.*
import kotlinx.android.synthetic.main.layout_status_operation.*

/**
 * 多状态视图 XML
 * @author Max
 * @date 2019-12-16.
 */
class StatusXmlFragment : BaseFragment() {

    val adapter = SimpleAdapter()

    override fun getLayoutResId(): Int {
        return R.layout.fragment_status_xml
    }

    override fun onInitialize() {
        super.onInitialize()
        initView()
        initEvent()
    }

    private fun initView() {
        // 添加自定义状态
        recyclerView.getStatusView()?.addStatus(CustomStatus, R.layout.layout_status_custom)

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
    }

    private fun initEvent() {

        statusView.setOnRetryClickListener(object : IStatusView.OnRetryClickListener {
            override fun onRetry(statusView: IStatusView, status: Status) {
                L.e("状态重试：status -> $status")
                statusView.switchStatus(Status.Loading)
                loading()
            }
        })

        statusView.setOnStatusChangeListener(object : IStatusView.OnStatusChangeListener {
            override fun onChange(newStatus: Status, oldStatus: Status) {
                L.e("状态改变：$newStatus -#- $oldStatus")
            }
        })

        tv_content.singleClick {
            statusView.showContent()
        }

        tv_loading.singleClick {
            statusView.showLoading()
        }

        tv_empty.singleClick {
            statusView.showEmpty()
        }

        tv_error.singleClick {
            statusView.showError()
        }

        tv_no_work.singleClick {
            statusView.showNoNetwork()
        }

        tv_custom.singleClick {
            statusView?.switchStatus(CustomStatus)
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
        statusView.switchStatus(status, 1000)
    }
}