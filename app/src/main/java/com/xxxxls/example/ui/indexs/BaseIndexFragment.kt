package com.xxxxls.example.ui.indexs

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xxxxls.adapter.IAdapter
import com.xxxxls.adapter.listener.OnItemClickListener
import com.xxxxls.example.R
import com.xxxxls.example.adapter.IndexItemAdapter
import com.xxxxls.example.bean.IndexItemBean
import com.xxxxls.module_base.base.BaseFragment
import com.xxxxls.titlebar.setTitleBarTitle
import kotlinx.android.synthetic.main.fragment_common_index.*

/**
 * 基础indexFragment
 * @author Max
 * @date 2020-01-07.
 */
abstract class BaseIndexFragment : BaseFragment() {

    protected val adapter: IndexItemAdapter by lazy {
        IndexItemAdapter().apply {
            submitList(getItems().toList())
        }
    }

    override fun getLayoutResId(): Int? {
        return R.layout.fragment_common_index
    }

    override fun onInitialize() {
        super.onInitialize()
        setTitleBarTitle(getTitle())

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        adapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                onItemClick(position, adapter.getItem(position)!!)
            }
        })
    }

    protected abstract fun getTitle(): String

    protected abstract fun getItems(): Array<IndexItemBean>

    protected abstract fun onItemClick(index: Int, item: IndexItemBean)
}