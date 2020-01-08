package com.xxxxls.utils

import android.os.Environment
import java.io.File


/**
 * 路径帮助类
 * @author Max
 * @date 2019-10-11.
 */
object PathHelper {

    /**
     * 获取cache文件夹(优先外部存储，与应用共存亡)
     */
    fun getCacheDirPath(): String {
        // 判断外部存储是否可用，如果不可用则使用内部存储路径
        return (if (Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()) {
            // storage/emulated/0/Android/data/packname/cache
            AppUtils.getApp().externalCacheDir!!.absolutePath
        } else {
            // 使用内部存储缓存目录
            // data/user/0/packname/cache
            AppUtils.getApp().cacheDir.absolutePath
        }).apply {
            FileUtils.createOrExistsDir(this)
        }
    }


    /**
     * 获取files文件夹(优先外部存储，与应用共存亡)
     */
    fun getFilesDirPath(): String {
        // 判断外部存储是否可用，如果不可用则使用内部存储路径
        return (if (Environment.MEDIA_MOUNTED == Environment.getExternalStorageState()) {
            // storage/emulated/0/Android/data/packname/files
            AppUtils.getApp().getExternalFilesDir("")!!.absolutePath
        } else {
            // 使用内部存储缓存目录
            // data/user/0/packname/files
            AppUtils.getApp().filesDir.absolutePath
        }).apply {
            FileUtils.createOrExistsDir(this)
        }
    }

    /**
     * 临时文件存储目录路径（每次打开app会被删除）
     */
    fun getTempDirPath(): String {
        return (getCacheDirPath() + File.separator + "Temp").apply {
            FileUtils.createOrExistsDir(this)
        }
    }

    /**
     * 获取公开的图片存储目录路径（与手机存亡）
     */
    fun getPublicPicturesDirPath(): String {
        return PathUtils.getExternalPicturesPath()
    }

    /**
     * 获取公开的电影存储目录路径（与手机存亡）
     */
    fun getPublicMoviesDirPath(): String {
        return PathUtils.getExternalMoviesPath()
    }

    /**
     * 获取公开的下载存储目录路径（与手机存亡）
     */
    fun getPublicDownloadsDirPath(): String {
        return PathUtils.getExternalDownloadsPath()
    }

    /**
     * 临时视频存储目录路径（每次打开app会被删除）
     */
    fun getTempVideoDirPath(): String {
        return (getTempDirPath() + File.separator + "Video").apply {
            FileUtils.createOrExistsDir(this)
        }
    }

    /**
     * 临时图片存储目录路径（每次打开app会被删除）
     */
    fun getTempPicturesDirPath(): String {
        return (getTempDirPath() + File.separator + "Pictures").apply {
            FileUtils.createOrExistsDir(this)
        }
    }

    /**
     * 视频存储目录路径（除非手动删除，否则与应用共存亡）
     */
    fun getCacheVideoDirPath(): String {
        return (getCacheDirPath() + File.separator + "Video").apply {
            FileUtils.createOrExistsDir(this)
        }
    }

    /**
     * 图片存储目录路径（除非手动删除，否则与应用共存亡）
     */
    fun getCachePicturesDirPath(): String {
        return (getCacheDirPath() + File.separator + "Pictures").apply {
            FileUtils.createOrExistsDir(this)
        }
    }


    /**
     * 获取一个新的 临时视频文件 路径
     */
    fun getNewTempVideoFilePath(name: String = System.currentTimeMillis().toString() + ".mp4"): String {
        return (getTempVideoDirPath() + File.separator + name).apply {
            FileUtils.createOrExistsFile(this)
        }
    }

    /**
     * 获取一个新的 临时图片文件 路径
     */
    fun getNewTempPicturesFilePath(name: String = System.currentTimeMillis().toString() + ".jpg"): String {
        return (getTempPicturesDirPath() + File.separator + name).apply {
            FileUtils.createOrExistsFile(this)
        }
    }


    //------------------------------ 分割线 ------------------------------ （上面是公共路径 ，下面是业务路径）


    /**
     * 获取视频草稿存储目录路径
     */
    fun getVideoDraftDirPath(): String {
        return (getFilesDirPath() + File.separator + "video_draft").apply {
            FileUtils.createOrExistsDir(this)
        }
    }

    /**
     * 获取一个新的 视频草稿文件路径
     */
    fun getNewVideoDraftFilePath(name: String = System.currentTimeMillis().toString() + ".mp4"): String {
        return (getVideoDraftDirPath() + File.separator + name).apply {
            FileUtils.createOrExistsFile(this)
        }
    }


    /**
     * 获取一个新的 视频草稿封面文件路径
     */
    fun getNewVideoDraftCoverFilePath(name: String = System.currentTimeMillis().toString() + ".jpg"): String {
        return (getVideoDraftDirPath() + File.separator + name).apply {
            FileUtils.createOrExistsFile(this)
        }
    }

}