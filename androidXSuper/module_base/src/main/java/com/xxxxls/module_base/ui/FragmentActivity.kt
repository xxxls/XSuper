package com.xxxxls.module_base.ui

import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.xxxxls.module_base.R
import com.xxxxls.module_base.component.BaseActivity
import com.xxxxls.module_base.constants.BasePaths
import com.xxxxls.module_base.util.newFragment
import com.xxxxls.utils.ktx.showFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * Fragment的Activity形式
 * @author Max
 * @date 2020-01-07.
 */
@AndroidEntryPoint
@Route(path = BasePaths.BASE_ACTIVITY_FRAGMENT)
class FragmentActivity : BaseActivity() {

    //fragment路径
    @JvmField
    @Autowired(name = BasePaths.KEY_ACTIVITY_FRAGMENT_PATH)
    var fragmentPath: String = ""

    override fun getLayoutResId(): Int {
        return R.layout.base_activity_fragment
    }

    override fun onInitialize() {
        super.onInitialize()
        fragmentPath.newFragment()?.let {
            showFragment(it, R.id.frameLayout)
        }
    }
}