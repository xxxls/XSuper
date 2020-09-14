package com.xxxxls.xsuper.clazz


/**
 * （类对象）提供者
 */
open class ClazzProvider(
    private val store: Store,
    private val factory: Factory
) {
    fun <T> get(clazz: Class<T>): T {
        var api = store.get(clazz)
        if (api == null) {
            api = factory.create(clazz)
            store.put(clazz, api)
        }
        return api!!
    }

    /**
     * （类对象）工厂
     * @author Max
     * @date 2020/9/14.
     */
    interface Factory {
        fun <T> create(clazz: Class<T>): T
    }

    /**
     * （类对象）存储器
     */
    interface Store {
        /**
         * 获取对象
         * @param key
         */
        fun <T> get(key: Class<T>): T?

        /**
         * 添加
         */
        fun <T> put(key: Class<T>, value: T)

        /**
         * 移除
         */
        fun <T> remove(key: Class<T>): T?

        /**
         * 清空
         */
        fun clear()
    }
}

