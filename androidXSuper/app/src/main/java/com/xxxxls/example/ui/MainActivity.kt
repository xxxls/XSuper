package com.xxxxls.example.ui

import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.xxxxls.example.R
import com.xxxxls.module_base.component.BaseActivity
import com.xxxxls.module_base.constants.HomePaths
import com.xxxxls.module_base.constants.UserPaths
import com.xxxxls.module_base.util.newFragment
import com.xxxxls.module_base.adapter.CommonFragmentPagerAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList


@AndroidEntryPoint
class MainActivity : BaseActivity() {


    override fun getLayoutResId(): Int {
        return R.layout.activity_main
    }

    override fun onInitialize() {
        super.onInitialize()
        initView()
        initEvent()
    }

    private fun initView() {
        val fragments = ArrayList<Fragment>()
        fragments.add(HomePaths.HOME_FRAGMENT_PAGING_INDEX.newFragment()!!)
        fragments.add(HomePaths.HOME_FRAGMENT_HOME_INDEXS.newFragment()!!)
        fragments.add(UserPaths.USER_FRAGMENT_INDEX.newFragment()!!)
        viewPager.adapter =
            CommonFragmentPagerAdapter(
                supportFragmentManager,
                fragments
            )
        viewPager.offscreenPageLimit = fragments.size
    }

    private fun initEvent() {

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                bottomNavigationView.menu.getItem(position).isChecked = true
            }
        })

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.item_home -> {
                    viewPager.currentItem = 0
                }
                R.id.item_tools -> {
                    viewPager.currentItem = 1
                }
                R.id.item_user -> {
                    viewPager.currentItem = 2
                }
                else -> {
                    return@setOnNavigationItemSelectedListener false
                }
            }
            true
        }
    }


}
