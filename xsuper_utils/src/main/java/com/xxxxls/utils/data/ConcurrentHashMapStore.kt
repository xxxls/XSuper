package com.xxxxls.utils.data

import java.util.concurrent.ConcurrentHashMap

/**
 * ConcurrentHashMap - 维护
 * @author Max
 * @date 2020/9/15.
 */
open class ConcurrentHashMapStore<K, V> :
    XSuperStore<K, V> {

    private val map: ConcurrentHashMap<K, V?> by lazy {
        ConcurrentHashMap<K, V?>()
    }

    override fun get(key: K): V? {
        return map[key]
    }

    override fun put(key: K, value: V?) {
        map[key] = value
    }

    override fun remove(key: K): V? {
        return map.remove(key)
    }

    override fun clear() {
        map.clear()
    }
}