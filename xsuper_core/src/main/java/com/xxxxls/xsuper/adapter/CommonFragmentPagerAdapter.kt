package com.xxxxls.xsuper.adapter


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * 常见FragmentPagerAdapter
 * @author Max
 * @date 2019-12-07.
 */
class CommonFragmentPagerAdapter (fragmentManager: FragmentManager,
                                  var fragments: List<Fragment>,
                                  var titles: List<String>? = null): FragmentPagerAdapter(fragmentManager) {

    override fun getItem(i: Int): Fragment {
        return fragments[i]
    }

    override fun getCount(): Int {
        return fragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        titles?.let {
            if (position <= it.size) {
                return it[position]
            }
        }
        return super.getPageTitle(position)
    }
}
