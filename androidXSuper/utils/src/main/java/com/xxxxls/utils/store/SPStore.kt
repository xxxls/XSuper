package com.xxxxls.utils.store

import android.content.Context
import android.content.SharedPreferences
import com.xxxxls.utils.AppUtils
import com.xxxxls.utils.store.IStore.Companion.DEFAULT_GROUP
import java.util.concurrent.ConcurrentHashMap

/**
 * SharedPreferences工具类
 * @author Max
 * @date 2020-01-08.
 */
object SPStore : IStore<SharedPreferences> {

    //多sharedPreferences
    private val preferenceMap: ConcurrentHashMap<String, SharedPreferences> by lazy {
        ConcurrentHashMap<String, SharedPreferences>()
    }

    override fun <T> getValue(key: String, default: T, group: String?): T? {
        val instance = getInstance(group) ?: return null
        return with(instance) {
            val value = when (default) {
                is String -> getString(key, default)
                is Int -> getInt(key, default)
                is Long -> getLong(key, default)
                is Float -> getFloat(key, default)
                is Boolean -> getBoolean(key, default)
                is Set<*> -> getStringSet(key, default as Set<String>)
                else -> throw IllegalArgumentException("This type of data can not be get! ")
            }
            value as T
        }
    }

    override fun <T> setValue(key: String, value: T, group: String?): Boolean {
        val instance = getInstance(group) ?: return false
        with(instance.edit()) {
            when (value) {
                is String -> putString(key, value as String)
                is Int -> putInt(key, value as Int)
                is Long -> putLong(key, value as Long)
                is Float -> putFloat(key, value as Float)
                is Boolean -> putBoolean(key, value as Boolean)
                is Set<*> -> putStringSet(key, value as Set<String>)
                else -> throw IllegalArgumentException("This type of data can not be saved! ")
            }.apply()
        }
        return true
    }

    override fun clear(group: String?) {
        val instance = getInstance(group) ?: return
        instance.edit().clear().apply()
    }

    override fun clearAll() {
        val instance = getInstance(DEFAULT_GROUP) ?: return
        instance.edit().clear().apply()
        // 待完善
    }

    override fun removeValue(key: String, group: String?): Boolean {
        val instance = getInstance(group) ?: return false
        instance.edit().remove(key).apply()
        return true
    }

    override fun containsKey(key: String, group: String?): Boolean {
        val instance = getInstance(group) ?: return false
        return instance.contains(key)
    }

    override fun getInstance(group: String?): SharedPreferences? {
        val name = group ?: DEFAULT_GROUP
        if (!preferenceMap.contains(name)) {
            synchronized(this) {
                if (!preferenceMap.contains(name)) {
                    preferenceMap[name] =
                        AppUtils.getApp().getSharedPreferences(group, Context.MODE_PRIVATE)
                }
            }
        }
        return preferenceMap[group]!!
    }
}
