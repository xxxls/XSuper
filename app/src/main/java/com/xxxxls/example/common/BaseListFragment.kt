package com.xxxxls.example.common

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xxxxls.adapter.listener.OnItemClickListener
import com.xxxxls.example.R
import com.xxxxls.example.adapter.SimpleListItemAdapter
import com.xxxxls.example.bean.SimpleItemBean
import com.xxxxls.module_base.component.BaseFragment
import com.xxxxls.titlebar.setTitleBarTitle
import kotlinx.android.synthetic.main.fragment_simple_list.*

/**
 * 基础- List - Fragment
 * @author Max
 * @date 2020-01-07.
 */
abstract class BaseListFragment : BaseFragment() {

    protected val adapter: SimpleListItemAdapter by lazy {
        SimpleListItemAdapter().apply {
            submitList(getItems().toList())
        }
    }

    override fun getLayoutResId(): Int? {
        return R.layout.fragment_simple_list
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

    protected abstract fun getItems(): Array<SimpleItemBean>

    protected abstract fun onItemClick(index: Int, item: SimpleItemBean)
}