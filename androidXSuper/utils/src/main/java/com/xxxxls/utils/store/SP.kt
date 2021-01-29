package com.xxxxls.utils.store

import com.xxxxls.utils.store.IStore.Companion.DEFAULT_GROUP
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * SharedPreferences - 委托属性
 * @param key
 * @param default 默认值
 * @param group SharedPreferences名称
 */
class SP<T>(val key: String, val default: T, val group: String = DEFAULT_GROUP) :
    ReadWriteProperty<Any?, T> {

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return SPStore.getValue(key = key, default = default, group = group) ?: default
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        SPStore.setValue(key = key, value = value, group = group)
    }
}