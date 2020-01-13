package com.xxxxls.utils.ktx

import com.xxxxls.utils.SPUtils
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * 委托属性
 * @param key
 * @param default 默认值
 * @param name SharedPreferences名称
 */
class SP<T>(val key: String, val default: T, val name: String = "config") :
    ReadWriteProperty<Any?, T> {

    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return SPUtils.getValue(key = key, default = default, name = name)
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        return SPUtils.setValue(key = key, value = value, name = name)
    }
}