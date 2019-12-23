package com.xxxxls.adapter.paging

/**
 * 列表状态
 * @author Max
 * @date 2019-12-20.
 */
sealed class XSuperListStatus(val retry: (() -> Unit)? = null) {

    /**
     * 是否正在获取中
     */
    fun isRuning(): Boolean {
        when (this) {
            is Initialize,
            is LoadMoreIn,
            is FrontLoadMoreIn -> {
                //正在刷新中
                return true
            }
        }
        return false
    }

    /**
     * 是否失败类型
     */
    fun isError(): Boolean {
        when (this) {
            is InitializeError,
            is LoadMoreError,
            is FrontLoadMoreError -> {
                return true
            }
        }
        return false
    }

    /**
     * 是否结束类型
     */
    fun isEnd(): Boolean {
        when (this) {
            is LoadMoreEnd,
            is FrontEnd,
            is Empty -> {
                return true
            }
        }
        return false
    }

    /**
     * 初始化中
     */
    object Initialize : XSuperListStatus()

    /**
     * 初始化成功
     */
    class InitializeSuccess(retry: (() -> Unit)? = null) : XSuperListStatus(retry)

    /**
     * 初始化错误
     */
    class InitializeError(retry: (() -> Unit)? = null) : XSuperListStatus(retry)

    /**
     * 列表空
     */
    class Empty(retry: (() -> Unit)? = null) : XSuperListStatus(retry)

    /**
     * 列表往下加载更多中
     */
    object LoadMoreIn : XSuperListStatus()

    /**
     * 列表往下加载成功
     */
    class LoadMoreSuccess(retry: (() -> Unit)? = null) : XSuperListStatus(retry)

    /**
     * 列表往下加载失败
     */
    class LoadMoreError(retry: (() -> Unit)? = null) : XSuperListStatus(retry)

    /**
     * 列表往下已全部加载完毕
     */
    class LoadMoreEnd(retry: (() -> Unit)? = null) : XSuperListStatus(retry)

    /**
     * 列表往上加载更多中
     */
    object FrontLoadMoreIn : XSuperListStatus()

    /**
     * 列表往上加载成功
     */
    class FrontLoadMoreSuccess(retry: (() -> Unit)? = null) : XSuperListStatus(retry)

    /**
     * 列表往上加载失败
     */
    class FrontLoadMoreError(retry: (() -> Unit)? = null) : XSuperListStatus(retry)

    /**
     * 列表往上已全部加载完毕
     */
    class FrontEnd(retry: (() -> Unit)? = null) : XSuperListStatus(retry)
}
