package com.xxxxls.utils.store

import com.xxxxls.utils.store.IStore.Companion.DEFAULT_GROUP
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * 存储器 - 委托属性
 * @param key
 * @param default 默认值
 * @param group 组名称
 * @param type 类型
 * @author Max
 * @date 1/29/21.
 */
class SuperStore<T>(
    val key: String,
    val default: T,
    val group: String = DEFAULT_GROUP,
    val type: Int = TYPE_MMKV
) :
    ReadWriteProperty<Any?, T> {

    companion object {
        const val TYPE_MMKV: Int = 0
        const val TYPE_SP: Int = 1
    }

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return when (type) {
            TYPE_SP -> {
                SPStore.getValue(key = key, default = default, group = group) ?: default
            }
            else -> {
                MMKVStore.getValue(key = key, default = default, group = group) ?: default
            }
        }
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        when (type) {
            TYPE_SP -> {
                SPStore.setValue(key = key, value = value, group = group)
            }
            else -> {
                MMKVStore.setValue(key = key, value = value, group = group)
            }
        }
    }
}