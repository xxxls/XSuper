package com.xxxxls.example.ui.tools.glide

import android.graphics.drawable.Drawable
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.xxxxls.example.R
import com.xxxxls.module_base.base.BaseActivity
import com.xxxxls.utils.L
import com.xxxxls.xsuper.utils.loadImage
import kotlinx.android.synthetic.main.activity_glide.*

/**
 * GlideActivity
 * @author Max
 * @date 2020-01-02.
 */
class GlideActivity : BaseActivity() {

    override fun getLayoutResId(): Int {
        return R.layout.activity_glide
    }

    override fun onInitialize() {
        super.onInitialize()

        val url1 = "http://www.qhlly.com/files/2013-1/20130122112200145553.jpg"

        val url2 = "http://img.duoziwang.com/2018/06/201712315281856.jpg"

        val url3 = "http://img.duoziwang.com/2018/06/201712315281856s.jpg"

        iv_a.loadImage(
            url1,
            skipMemory = true,
            diskCacheStrategy = DiskCacheStrategy.NONE,
            defaultImage = R.drawable.ic_launcher_background,
            errorImage = R.drawable.bg_test,
            circle = true)

        iv_a2.loadImage(
            url1,
            skipMemory = true,
            diskCacheStrategy = DiskCacheStrategy.NONE,
            defaultImage = R.drawable.ic_launcher_background,
            errorImage = R.drawable.bg_test,
            corners = 20
        )

        iv_b.loadImage(
            url1,
            defaultImage = R.drawable.ic_launcher_background,
            errorImage = R.drawable.bg_test,
            width = 300,
            height = 50
        )
        iv_b2.loadImage(
            url1,
            defaultImage = R.drawable.ic_launcher_background,
            errorImage = R.drawable.bg_test
        )

        iv_c.loadImage(
            url2,
            defaultImage = R.drawable.ic_launcher_background,
            errorImage = R.drawable.bg_test,
            circle = true
        )
        iv_c2.loadImage(
            url2,
            defaultImage = R.drawable.ic_launcher_background,
            errorImage = R.drawable.bg_test
        )

        iv_d.loadImage(
            url3,
            circle = true,
            defaultImage = R.drawable.ic_launcher_background,
            errorImage = R.drawable.bg_test
        )
        iv_d2.loadImage(
            resId = R.mipmap.ic_launcher,
            corners = 20,
            defaultImage = R.drawable.ic_launcher_background,
            errorImage = R.drawable.bg_test
        )
    }
}