package com.xxxxls.module_base.net

import com.xxxxls.xsuper.net.engine.XSuperHttpEngine
import java.util.concurrent.ConcurrentHashMap

/**
 * 网络请求引擎管理器
 * @author Max
 * @date 2019-12-06.
 */
class HttpEngineManager private constructor() {

    //引擎集合（大多数的场景都是使用同一个请求引擎，可能个别需求是不同模块或不同功能 访问不同的服务器地址，这个时候需要自己维护请求引擎了）
    private val mEngineMap: ConcurrentHashMap<String, XSuperHttpEngine> by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
        ConcurrentHashMap<String, XSuperHttpEngine>()
    }

    companion object {

        @Volatile
        private var instance: HttpEngineManager? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: HttpEngineManager().also { instance = it }
            }
    }

    /**
     * 构建引擎（已有记录时，直接返回，否则构造保存）
     * @param baseUrl 基础URL
     * @param build 构建引擎（当无本地缓存时，需要你构造一个出来）
     */
    fun buildEngine(baseUrl: String, build: () -> XSuperHttpEngine): XSuperHttpEngine {
        getHttpEngine(baseUrl)?.let {
            return it
        } ?: let {
            val engine = build()
            addHttpEngine(engine)
            return engine
        }
    }

    /**
     * 获取引擎
     * @param baseUrl 请求地址
     */
    fun getHttpEngine(baseUrl: String): XSuperHttpEngine? {
        return mEngineMap[baseUrl]
    }

    /**
     * 保存引擎
     * @param httpEngine 引擎
     */
    fun addHttpEngine(httpEngine: XSuperHttpEngine) {
        mEngineMap[httpEngine.getBaseUrl()] = httpEngine
    }

    //请求所有引擎
    fun clearAllEngine() {
        mEngineMap.clear()
    }
}