package com.xxxxls.utils

import android.content.Context
import android.content.SharedPreferences
import java.util.concurrent.ConcurrentHashMap

/**
 * SharedPreferences工具类
 * @author Max
 * @date 2020-01-08.
 */
object SPUtils {

    //多sharedPreferences
    private val preferenceMap: ConcurrentHashMap<String, SharedPreferences> by lazy {
        ConcurrentHashMap<String, SharedPreferences>()
    }

    //清除
    fun clear(name: String = "config") {
        getSharedPreferences(name).edit().clear().apply()
    }

    //获取SP
    fun getSharedPreferences(name: String): SharedPreferences {
        if (!preferenceMap.contains(name)) {
            synchronized(this) {
                if (!preferenceMap.contains(name)) {
                    val preference =
                        AppUtils.getApp().getSharedPreferences(name, Context.MODE_PRIVATE)
                    preferenceMap[name] = preference
                }
            }
        }
        return preferenceMap[name]!!
    }

    /**
     * 设置值
     * @param key
     * @param value
     * @param name SharedPreferences名称
     */
    fun <T> setValue(key: String, value: T, name: String) {
        with(getSharedPreferences(name).edit()) {
            when (value) {
                is String -> putString(key, value as String)
                is Int -> putInt(key, value as Int)
                is Long -> putLong(key, value as Long)
                is Float -> putFloat(key, value as Float)
                is Boolean -> putBoolean(key, value as Boolean)
                else -> throw IllegalArgumentException("This type of data can not be saved! ")
            }.apply()
        }
    }

    /**
     * 获取值
     * @param key
     * @param default 默认值
     * @param name SharedPreferences名称
     */
    fun <T> getValue(key: String, default: T, name: String): T {
        return with(getSharedPreferences(name)) {
            val value = when (default) {
                is String -> getString(key, default)
                is Int -> getInt(key, default)
                is Long -> getLong(key, default)
                is Float -> getFloat(key, default)
                is Boolean -> getBoolean(key, default)
                else -> throw IllegalArgumentException("This type of data can not be get! ")
            }
            value as T
        }
    }
}
