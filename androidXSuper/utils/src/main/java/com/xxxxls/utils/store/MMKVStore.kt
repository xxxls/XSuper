package com.xxxxls.utils.store

import android.os.Parcelable
import com.tencent.mmkv.MMKV
import com.xxxxls.utils.store.IStore.Companion.DEFAULT_GROUP
import java.util.concurrent.ConcurrentHashMap

/**
 * MMKV 工具类
 * @author Max
 * @date 1/28/21.
 */
object MMKVStore : IStore<MMKV> {

    //多MMKV
    private val preferenceMap: ConcurrentHashMap<String, MMKV> by lazy {
        ConcurrentHashMap<String, MMKV>()
    }

    override fun <T> getValue(key: String, default: T, group: String?): T? {
        val mmkv = getInstance(group) ?: return null
        return with(mmkv) {
            val value = when (default) {
                is String -> getString(key, default)
                is Int -> getInt(key, default)
                is Long -> getLong(key, default)
                is Float -> getFloat(key, default)
                is Double -> decodeDouble(key, default)
                is Boolean -> getBoolean(key, default)
                is ByteArray -> getBytes(key, default)
                is Parcelable -> decodeParcelable(key, default.javaClass)
                else -> throw IllegalArgumentException("This type of data can not be get! ")
            }
            value as T
        }
    }

    override fun <T> setValue(key: String, value: T, group: String?): Boolean {
        val mmkv = getInstance(group) ?: return false
        with(mmkv) {
            when (value) {
                is String -> putString(key, value as String)
                is Int -> putInt(key, value as Int)
                is Long -> putLong(key, value as Long)
                is Float -> putFloat(key, value as Float)
                is Double -> encode(key, value as Double)
                is Boolean -> putBoolean(key, value as Boolean)
                is Parcelable -> encode(key, value as Parcelable)
                else -> throw IllegalArgumentException("This type of data can not be saved! ")
            }
        }
        return true
    }

    /**
     * 获取Parcelable
     */
    inline fun <reified T : Parcelable> getParcelable(key: String, group: String?): T? {
        val mmkv = getInstance(group) ?: return null
        return mmkv.decodeParcelable(key, T::class.java)
    }

    /**
     * 存储Parcelable
     */
    inline fun <reified T : Parcelable> setParcelable(key: String, value: T, group: String?): Boolean {
        val mmkv = getInstance(group) ?: return false
        return mmkv.encode(key, value)
    }

    override fun clear(group: String?) {
        val mmkv = getInstance(group) ?: return
        mmkv.clear()
    }

    override fun clearAll() {
        val mmkv = getInstance(null) ?: return
        mmkv.clearAll()
        // TODO 这里待完善
    }

    override fun removeValue(key: String, group: String?): Boolean {
        val mmkv = getInstance(null) ?: return false
        mmkv.remove(key)
        return true
    }

    override fun containsKey(key: String, group: String?): Boolean {
        val mmkv = getInstance(null) ?: return false
        return mmkv.containsKey(key)
    }

    override fun getInstance(group: String?): MMKV? {
        val name = group ?: DEFAULT_GROUP
        if (!preferenceMap.contains(name)) {
            synchronized(this) {
                if (!preferenceMap.contains(name)) {
                    preferenceMap[name] = (return if (name == DEFAULT_GROUP) {
                        MMKV.defaultMMKV()
                    } else {
                        MMKV.mmkvWithID(name)
                    })
                }
            }
        }
        return preferenceMap[name]!!
    }
}