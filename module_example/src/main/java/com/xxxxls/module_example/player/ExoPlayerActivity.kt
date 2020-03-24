package com.xxxxls.module_example.player

import com.google.android.exoplayer2.DefaultLoadControl
import com.google.android.exoplayer2.DefaultRenderersFactory
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.xxxxls.module_base.base.BaseActivity


/**
 * EXO 播放器
 * @author Max
 * @date 2020/3/25.
 */
class ExoPlayerActivity : BaseActivity() {

    private val player: ExoPlayer by lazy {
        ExoPlayerFactory.newSimpleInstance(
            this,
            DefaultRenderersFactory(this),
            DefaultTrackSelector(), DefaultLoadControl()
        )
    }

    override fun onInitialize() {
        super.onInitialize()
    }

    private fun initPlayer() {

    }

    /**
     * 设置显示大小
     * @param videoWidth 视频宽度
     * @param videoHeight 视频高度
     * @param rotate 旋转角度
     */
    fun resetDisplaySize(videoWidth:Float,videoHeight:Float,rotate:Int){

        // 播放视频大小比例
        val videoRatio = 0.5625f

        // 播放控件大小
        val viewWidth = 9f
        val viewHeight = 16f

        // 播放控件大小比例
        val viewRatio = 0.5625f

        // 最终显示大小
        var displayWidth = 0f
        var displayHeight = 0f

        if(videoRatio > viewRatio)
        {
            // 宽度铺满，高度计算
            displayWidth = viewWidth
            displayHeight = viewWidth / viewRatio
        }else
        {
            // 高度铺满，宽度计算
            displayWidth = viewHeight * viewRatio
            displayHeight = viewHeight
        }

        // 缩放大小
        var scaleX = displayWidth / viewWidth
        var scaleY = displayHeight / viewHeight

        // 设置缩放..

        //


    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }
}