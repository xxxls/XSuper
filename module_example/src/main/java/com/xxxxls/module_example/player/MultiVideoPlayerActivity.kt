package com.xxxxls.module_example.player

import android.graphics.SurfaceTexture
import android.media.MediaPlayer
import android.view.Surface
import android.view.TextureView
import com.alibaba.android.arouter.facade.annotation.Route
import com.xxxxls.module_base.component.BaseActivity
import com.xxxxls.module_base.constants.ExamplePaths.EXAMPLE_ACTIVITY_MULTI_VIDEO
import com.xxxxls.module_example.R
import kotlinx.android.synthetic.main.example_activity_multi_video.*

/**
 * 多视频无缝播放
 * @author Max
 * @date 2020-03-20.
 */
@Route(path = EXAMPLE_ACTIVITY_MULTI_VIDEO)
class MultiVideoPlayerActivity : BaseActivity(), TextureView.SurfaceTextureListener {


    private val videoPathList = ArrayList<String>().apply {
        add("/storage/emulated/0/Download/1570242982060.mp4")
        add("/storage/emulated/0/Download/VID_20200320_004914.mp4")
        add("/storage/emulated/0/Download/1570193438117.mp4")
    }

    private var surface: Surface? = null

    private var currentMediaPlayer: MediaPlayer? = null

    private var cacheMediaPlayer: MediaPlayer? = null

    private var currentPlayIndex: Int = 0

    override fun getLayoutResId(): Int {
        return R.layout.example_activity_multi_video
    }

    override fun onInitialize() {
        super.onInitialize()
        textureView.surfaceTextureListener = this
    }

    private fun initMediaPlayer() {
        play(0)
    }

    private fun play(position: Int) {
        val path = getVideoPath(position)
        val nextPath = getVideoPath(position + 1)
        currentMediaPlayer?.setSurface(null)
        currentMediaPlayer = cacheMediaPlayer
        if (currentMediaPlayer == null) {
            currentMediaPlayer = createMediaPlayer(path)
        }
        currentMediaPlayer?.setSurface(surface)
        currentMediaPlayer?.start()

        cacheMediaPlayer = createMediaPlayer(nextPath)
    }

    private fun createMediaPlayer(path: String): MediaPlayer {
        return MediaPlayer().apply {
            this.setDataSource(path)
            this.setOnCompletionListener {
                currentPlayIndex++
                play(currentPlayIndex)
            }
            this.prepare()
        }
    }

    override fun onSurfaceTextureSizeChanged(surface: SurfaceTexture?, width: Int, height: Int) {
    }

    override fun onSurfaceTextureUpdated(surface: SurfaceTexture?) {
    }

    override fun onSurfaceTextureDestroyed(surface: SurfaceTexture?): Boolean {
        return true
    }

    override fun onSurfaceTextureAvailable(surface: SurfaceTexture?, width: Int, height: Int) {
        this.surface = Surface(surface)
        initMediaPlayer()
    }

    private fun getVideoIndex(index: Int): Int {
        return index % videoPathList.size
    }

    private fun getVideoPath(index: Int): String {
        return videoPathList[getVideoIndex(index)]
    }


}
