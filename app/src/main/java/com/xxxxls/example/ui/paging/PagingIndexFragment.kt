package com.xxxxls.example.ui.paging

import com.xxxxls.example.R
import com.xxxxls.example.ui.paging.item_keyed.ItemKeyedFragment
import com.xxxxls.example.ui.paging.page_keyed.PageKeyedFragment
import com.xxxxls.example.ui.paging.positional.PositionalFragment
import com.xxxxls.module_base.base.BaseFragment
import com.xxxxls.xsuper.adapter.CommonFragmentPagerAdapter
import kotlinx.android.synthetic.main.fragment_paging_index.*
import kotlinx.android.synthetic.main.fragment_paging_index.viewPager

/**
 * paging 主页
 * @author Max
 * @date 2019-12-23.
 */
class PagingIndexFragment : BaseFragment() {

    override fun getLayoutResId(): Int? {
        return R.layout.fragment_paging_index
    }

    override fun onInitialize() {
        super.onInitialize()

        val fragments = arrayListOf(PositionalFragment(), ItemKeyedFragment(), PageKeyedFragment())

        val tabs = arrayListOf<String>("positional", "itemKeyed", "pageKeyed")

        viewPager.adapter = CommonFragmentPagerAdapter(
            fragmentManager = childFragmentManager,
            fragments = fragments,
            titles = tabs
        )
        tabLayout.setupWithViewPager(viewPager)
        viewPager.offscreenPageLimit = fragments.size
    }
}