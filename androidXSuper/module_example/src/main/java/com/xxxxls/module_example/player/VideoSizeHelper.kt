package com.xxxxls.module_example.player

import com.xxxxls.utils.L

/**
 * @author Max
 * @date 2020/3/25.
 */
class VideoSizeHelper {


    /**
     * 设置显示大小
     * @param videoWidth 视频宽度
     * @param videoHeight 视频高度
     * @param rotate 旋转角度
     */
    fun resetDisplaySize(videoWidth: Float, videoHeight: Float, rotate: Int) {

        // 是否旋转视频画面（垂直变横向）
        val isHasRotate = (rotate / 90) % 2 == 1

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
            displayHeight = viewWidth / viewRatio
        } else {
            // 高度铺满，宽度计算
            displayWidth = viewHeight * viewRatio
            displayHeight = viewHeight
        }

        if (isHasRotate) {
            // 有旋转，宽高替换
            val temp = displayWidth
            displayWidth = displayHeight
            displayHeight = temp
        }

        // 缩放大小
        var scaleX = displayWidth / viewWidth
        var scaleY = displayHeight / viewHeight

        // 设置缩放
        L.e("")

    }
}