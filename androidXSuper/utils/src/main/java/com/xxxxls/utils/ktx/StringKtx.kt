package com.xxxxls.utils.ktx

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import com.xxxxls.utils.AppUtils


//<editor-fold desc="复制到剪切板">
/**
 * 复制到剪切板
 */
fun String.copyToClipboard(): Boolean {
    return try {
        //获取剪贴板管理器
        val clipboardManager = AppUtils.getApp().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        // 创建普通字符型ClipData
        val clipData = ClipData.newPlainText("Label", this)
        // 将ClipData内容放到系统剪贴板里。
        clipboardManager.setPrimaryClip(clipData)
        true
    } catch (e: Exception) {
        false
    }
}

//</editor-fold>
