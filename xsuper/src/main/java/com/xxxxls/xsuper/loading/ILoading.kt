package com.xxxxls.xsuper.loading

import com.xxxxls.utils.main
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * 进度视图
 * @author Max
 * @date 2019-11-26.
 */
interface ILoading {

    /**
     * 展示进度
     * @param id
     * @param message 消息
     */
    fun showLoading(id: Int? = null, message: CharSequence? = null)

    /**
     * 关闭进度
     * @param id
     */
    fun dismissLoading(id: Int? = null)
}

/**
 * 协程 - 主线程展示
 */
suspend fun ILoading?.showLoadingInCoroutine(id: Int? = null, message: CharSequence? = null) {
    withContext(Dispatchers.Main) {
        this@showLoadingInCoroutine?.showLoading(id, message)
    }
}

/**
 * 协程 - 主线程销毁
 */
suspend fun ILoading?.dismissLoadingInCoroutine(id: Int? = null) {
    withContext(Dispatchers.Main) {
        this@dismissLoadingInCoroutine?.dismissLoading(id)
    }
}

/**
 * 主线程 - 展示
 */
fun ILoading?.showLoadingInMain(id: Int? = null, message: CharSequence? = null) {
    main {
        this@showLoadingInMain?.showLoading(id, message)
    }
}

/**
 * 主线程 - 销毁
 */
fun ILoading?.dismissLoadingInMain(id: Int? = null) {
    main {
        this@dismissLoadingInMain?.dismissLoading(id)
    }
}