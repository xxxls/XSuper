package com.xxxxls.utils.data


/**
 * Map 存储器
 * @author Max
 * @date 2020/9/14.
 */
interface XSuperStore<K, V> {
    /**
     * 获取
     */
    fun get(key: K): V?

    /**
     * 获取/创建并获取
     */
    fun get(key: K, build: (K) -> V): V {
        var value = get(key)
        if (value == null) {
            value = build(key)
            put(key, value)
        }
        return value!!
    }

    /**
     * 设置
     */
    fun put(key: K, value: V?)

    /**
     * 移除
     */
    fun remove(key: K): V?

    /**
     * 清空
     */
    fun clear()
}
