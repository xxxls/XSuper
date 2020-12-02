package com.xxxxls.example.ui.paging

import com.alibaba.android.arouter.facade.annotation.Route
import com.xxxxls.example.R
import com.xxxxls.module_base.component.BaseFragment
import com.xxxxls.module_base.constants.HomePaths
import com.xxxxls.module_base.util.newFragment
import com.xxxxls.module_base.adapter.CommonFragmentPagerAdapter
import kotlinx.android.synthetic.main.fragment_paging_index.*
import kotlinx.android.synthetic.main.fragment_paging_index.viewPager

/**
 * paging 主页
 * @author Max
 * @date 2019-12-23.
 */
@Route(path = HomePaths.HOME_FRAGMENT_PAGING_INDEX)
class PagingIndexFragment : BaseFragment() {

    override fun getLayoutResId(): Int? {
        return R.layout.fragment_paging_index
    }

    override fun onInitialize() {
        super.onInitialize()

        val fragments = arrayListOf(
            HomePaths.HOME_FRAGMENT_PAGING_LIST.newFragment(HomePaths.KEY_HOME_FRAGMENT_PAGING_LIST_TYPE to 0)!!,
            HomePaths.HOME_FRAGMENT_PAGING_LIST.newFragment(HomePaths.KEY_HOME_FRAGMENT_PAGING_LIST_TYPE to 1)!!,
            HomePaths.HOME_FRAGMENT_PAGING_LIST.newFragment(HomePaths.KEY_HOME_FRAGMENT_PAGING_LIST_TYPE to 2)!!
        )

        val tabs = arrayListOf("positional", "itemKeyed", "pageKeyed")

        viewPager.adapter =
            CommonFragmentPagerAdapter(
                fragmentManager = childFragmentManager,
                fragments = fragments,
                titles = tabs
            )
        tabLayout.setupWithViewPager(viewPager)
//        viewPager.offscreenPageLimit = fragments.size
    }
}