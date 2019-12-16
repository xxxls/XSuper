package com.xxxxls.example.ui.status

import android.os.Handler
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xxxxls.example.R
import com.xxxxls.module_base.adapter.SimpleAdapter
import com.xxxxls.module_base.base.BaseFragment
import com.xxxxls.status.IStatusView
import com.xxxxls.status.XStatus
import com.xxxxls.status.XStatusFactory
import com.xxxxls.status.XSuperStatusView
import com.xxxxls.utils.L
import com.xxxxls.utils.singleClick
import kotlinx.android.synthetic.main.fragment_status_xml.*
import kotlinx.android.synthetic.main.fragment_status_xml.recyclerView
import kotlinx.android.synthetic.main.layout_status_operation.*

/**
 * 多状态视图 CODE
 * @author Max
 * @date 2019-12-16.
 */
class StatusCodeFragment : BaseFragment() {

    val adapter = SimpleAdapter()

    var statusView:XSuperStatusView? = null

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

        statusView = XStatusFactory.status(
            this,
            loadingRes = R.layout.base_status_loading,
            errorRes = R.layout.base_status_error,
            emptyRes = R.layout.base_status_empty,
            noNetworkRes = R.layout.base_status_no_network,
            onRetry = { statusView: IStatusView, status: XStatus ->
                L.e("状态重试：status -> $status")
                loading()
                true
            }
        )

    }

    private fun initEvent() {

        tv_content.singleClick {
            statusView?.showContent()
        }

        tv_loading.singleClick {
            statusView?.showLoading()
        }

        tv_empty.singleClick {
            statusView?.showEmpty()
        }

        tv_error.singleClick {
            statusView?.showError()
        }

        tv_no_work.singleClick {
            statusView?.showNoNetwork()
        }

    }

    private fun loading() {
        Handler().postDelayed({
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
            statusView?.switchStatus(status)
        }, 1000)
    }
}