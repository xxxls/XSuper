package com.xxxxls.utils.store

/**
 * 存储器
 * @author Max
 * @date 1/28/21.
 */
interface IStore<T> {

    companion object {
        const val DEFAULT_GROUP: String = "default"
    }

    /**
     * 获取操作实例
     * @param group
     */
    fun getInstance(group: String?): T?

    /**
     * 获取值
     * @param key 键
     * @param default 默认值
     * @param group 组
     */
    fun <T> getValue(key: String, default: T, group: String? = null): T?

    /**
     * 存储值
     * @param key 键
     * @param value 值
     * @param group 组
     */
    fun <T> setValue(key: String, value: T, group: String? = null): Boolean

    /**
     * 清除指定组的所有值
     * @param group 组
     */
    fun clear(group: String? = null)

    /**
     * 清除所有
     */
    fun clearAll()

    /**
     * 移除值
     * @param key 键
     * @param group 组
     */
    fun removeValue(key: String, group: String? = null): Boolean

    /**
     * 指定键是否存在
     * @param key 键
     * @param group 组
     */
    fun containsKey(key: String, group: String? = null): Boolean
}