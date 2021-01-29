package com.xxxxls.utils.store

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * MMKV委托属性
 * @author Max
 * @date 1/28/21.
 * @param key 键
 * @param default 默认值
 * @param group 组名称
 */
class MMKVP<T>(
    private val key: String,
    private val default: T,
    private val group: String? = null
) :
    ReadWriteProperty<Any?, T> {

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return MMKVStore.getValue(key = key, default = default, group = group) ?: default
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        MMKVStore.setValue(key = key, value = value, group = group)
    }
}