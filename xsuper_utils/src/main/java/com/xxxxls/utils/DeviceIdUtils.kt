package com.xxxxls.utils

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Environment
import androidx.core.app.ActivityCompat
import java.io.*
import java.security.MessageDigest
import java.util.*

/**
 * 设备ID
 * @author Max
 * @date 2020-01-08.
 */
object DeviceIdUtils {

    //保存的文件 采用隐藏文件的形式进行保存
    private val DEVICES_FILE_NAME = ".DEVICES"

    //内存缓存
    private var deviceId: String? = null

    /**
     * 获取设备ID
     */
    @Synchronized
    fun getDeviceId(): String {
        if (deviceId.isNullOrEmpty()) {
            deviceId = createDeviceId()
        }
        return deviceId!!
    }

    /**
     * 获得设备序列号（如：WTK7N16923005607）, 个别设备无法获取
     *
     * @return 设备序列号
     */
    private val serial: String?
        get() {
            try {
                return Build.SERIAL
            } catch (ex: Exception) {
                ex.printStackTrace()
            }

            return ""
        }

    /**
     * 获得设备硬件uuid
     * 使用硬件信息，计算出一个随机数
     *
     * @return 设备硬件uuid
     */
    private val deviceUUID: String
        get() {
            try {
                val dev = "20190415" +
                        Build.BOARD.length % 10 +
                        Build.BRAND.length % 10 +
                        Build.DEVICE.length % 10 +
                        Build.HARDWARE.length % 10 +
                        Build.ID.length % 10 +
                        Build.MODEL.length % 10 +
                        Build.PRODUCT.length % 10 +
                        Build.SERIAL.length % 10

                return UUID(
                    dev.hashCode().toLong(),
                    Build.SERIAL.hashCode().toLong()
                ).toString()
            } catch (ex: Exception) {
                ex.printStackTrace()
                return ""
            }
        }

    /**
     * 创建ID并存储本地
     */
    private fun createDeviceId(): String {
        var deviceId = readDeviceIdFromFile(AppUtils.getApp())
        if (deviceId.isNullOrEmpty()) {
            deviceId = getAppDeviceId(AppUtils.getApp())
            CoroutineUtils.io {
                saveDeviceId(deviceId)
            }
        }
        return deviceId
    }

    /**
     * 保存本地
     */
    private fun saveDeviceId(deviceId: String) {
        val file = getDevicesDir(AppUtils.getApp())
        var fos: FileOutputStream? = null
        var out: Writer? = null
        try {
            fos = FileOutputStream(file)
            out = OutputStreamWriter(fos, "UTF-8")
            out.write(deviceId)
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                if (fos != null) {
                    fos.flush()
                    fos.close()
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
    }

    /**
     * 读取本地记录
     */
    private fun readDeviceIdFromFile(context: Context): String? {
        val file = getDevicesDir(context)
        val buffer = StringBuffer()
        return try {
            val fis = FileInputStream(file)
            val isr = InputStreamReader(fis, "UTF-8")
            val inReader = BufferedReader(isr)
            var i = inReader.read()
            while (i > -1) {
                buffer.append(i.toChar())
                i = inReader.read()
            }
            inReader.close()
            buffer.toString()
        } catch (e: Exception) {
            null
        }
    }

    /**
     * 统一处理设备唯一标识 保存的文件的地址
     * @param context
     * @return
     */
    private fun getDevicesDir(context: Context): File {
        val mCropFile: File
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) == PackageManager.PERMISSION_GRANTED && Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
        ) {
            val absolutePath =
                Environment.getExternalStorageDirectory().absolutePath + File.separator + "deviceId"
            val cropdir = File(absolutePath, context.packageName)
            if (!cropdir.exists()) {
                cropdir.mkdirs()
            }
            // 用当前时间给取得的图片命名
            mCropFile = File(cropdir, DEVICES_FILE_NAME)
        } else {
            val cropdir = File(context.filesDir.absolutePath)
            if (!cropdir.exists()) {
                cropdir.mkdirs()
            }
            mCropFile = File(cropdir, DEVICES_FILE_NAME)
        }
        return mCropFile
    }


    @SuppressLint("MissingPermission")
    private fun getAppDeviceId(context: Context): String {
        val sbDeviceId = StringBuffer()
        //获得设备默认IMEI（>=6.0 需要ReadPhoneState权限）
        val imei = PhoneUtils.getIMEI()
        //获得AndroidId（无需权限）
        val androidid = PhoneUtils.getAndroidId()
        //获得设备序列号（无需权限）
        val serial = serial
        //获得硬件uuid（根据硬件相关属性，生成uuid）（无需权限）
        val uuid = deviceUUID.replace("-", "")
        //追加imei
        if (imei != null && imei.isNotEmpty()) {
            sbDeviceId.append(imei)
            sbDeviceId.append("|")
        }
        //追加androidid
        if (androidid != null && androidid.isNotEmpty()) {
            sbDeviceId.append(androidid)
            sbDeviceId.append("|")
        }
        //追加serial
        if (serial != null && serial.isNotEmpty()) {
            sbDeviceId.append(serial)
            sbDeviceId.append("|")
        }
        //追加硬件uuid
        if (uuid.isNotEmpty()) {
            sbDeviceId.append(uuid)
        }

        //生成SHA1，统一DeviceId长度
        if (sbDeviceId.isNotEmpty()) {
            try {
                val hash = getHashByString(sbDeviceId.toString())
                val sha1 = bytesToHex(hash)
                if (sha1.isNotEmpty()) {
                    //返回最终的DeviceId
                    return sha1
                }
            } catch (ex: Exception) {
                ex.printStackTrace()
            }

        }

        //如果以上硬件标识数据均无法获得，
        //则DeviceId默认使用系统随机数，这样保证DeviceId不为空
        return UUID.randomUUID().toString().replace("-", "")

    }

    /**
     * 取SHA1
     * @param data 数据
     * @return 对应的hash值
     */
    private fun getHashByString(data: String): ByteArray {
        return try {
            val messageDigest = MessageDigest.getInstance("SHA1")
            messageDigest.reset()
            messageDigest.update(data.toByteArray(charset("UTF-8")))
            messageDigest.digest()
        } catch (e: Exception) {
            "".toByteArray()
        }

    }

    /**
     * 转16进制字符串
     * @param data 数据
     * @return 16进制字符串
     */
    private fun bytesToHex(data: ByteArray): String {
        val sb = StringBuilder()
        var stmp: String
        for (n in data.indices) {
            stmp = Integer.toHexString(data[n].toInt() and 0xFF)
            if (stmp.length == 1) {
                sb.append("0")
            }
            sb.append(stmp)
        }
        return sb.toString().toUpperCase(Locale.CHINA)
    }
}