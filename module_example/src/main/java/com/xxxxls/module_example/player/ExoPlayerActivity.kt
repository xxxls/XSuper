package com.xxxxls.module_example.player

import android.graphics.Matrix
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

    private val matrix: Matrix by lazy {
        Matrix()
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
    fun resetDisplaySize(videoWidth: Float, videoHeight: Float, rotate: Float) {

        // 是否旋转视频画面（垂直变横向）
        val isHasRotate = (rotate / 90) % 2 == 1f

        // 播放视频大小比例
        var videoRatio = videoWidth / videoHeight

        if (isHasRotate) {
            // 有旋转，反向比例计算
            videoRatio = videoHeight / videoWidth
        }

        // 播放控件大小
        val viewWidth = 9f
        val viewHeight = 16f

        // 播放控件大小比例
        val viewRatio = viewWidth / viewHeight

        // 最终显示大小
        var displayWidth = 0f
        var displayHeight = 0f

        if (videoRatio > viewRatio) {
            // 宽度铺满，高度计算
            displayWidth = viewWidth
            displayHeight = viewWidth / videoRatio
        } else {
            // 高度铺满，宽度计算
            displayWidth = viewHeight * videoRatio
            displayHeight = viewHeight
        }

        if (isHasRotate) {
            // 有旋转，宽高替换
            val temp = displayWidth
            displayWidth = displayHeight
            displayHeight = temp
        }

        // 缩放大小
        val scaleX = displayWidth / viewWidth
        val scaleY = displayHeight / viewHeight

        // 设置缩放
        matrix.setScale(scaleX, scaleY, viewHeight / 2, viewHeight / 2)

        // 设置旋转
        matrix.postRotate(rotate,viewHeight / 2, viewHeight / 2)
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
    }
}