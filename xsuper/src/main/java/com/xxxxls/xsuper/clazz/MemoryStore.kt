package com.xxxxls.xsuper.clazz

import java.util.concurrent.ConcurrentHashMap

/**
 * * 内存-存储器
 * @author Max
 * @date 2020/9/14.
 */
open class MemoryStore : ClazzProvider.Store {

    private val cache: ConcurrentHashMap<String, Any> by lazy {
        ConcurrentHashMap<String, Any>()
    }

    override fun <T> get(key: Class<T>): T? {
        return cache[key.canonicalName] as? T
    }

    override fun <T> put(key: Class<T>, value: T) {
        cache[key.canonicalName!!] = value as Any
    }

    override fun <T> remove(key: Class<T>): T? {
        return cache.remove(key.canonicalName!!) as? T
    }

    override fun clear() {
        cache.clear()
    }

}