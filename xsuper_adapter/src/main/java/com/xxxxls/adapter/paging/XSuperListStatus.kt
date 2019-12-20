package com.xxxxls.adapter.paging

/**
 * 列表状态
 * @author Max
 * @date 2019-12-20.
 */
sealed class XSuperListStatus {


    /**
     * 初始化中
     */
    object Initialize : XSuperListStatus()

    /**
     * 初始化成功
     */
    object InitializeSuccess : XSuperListStatus()

    /**
     * 初始化错误
     */
    object InitializeError : XSuperListStatus()

    /**
     * 列表空
     */
    object Empty : XSuperListStatus()

    /**
     * 列表往下加载更多中
     */
    object LoadMoreIn : XSuperListStatus()

    /**
     * 列表往下加载成功
     */
    object LoadMoreSuccess : XSuperListStatus()

    /**
     * 列表往下加载失败
     */
    object LoadMoreError : XSuperListStatus()

    /**
     * 列表往下已全部加载完毕
     */
    object End : XSuperListStatus()

    /**
     * 列表往上加载更多中
     */
    object AtFrontLoadMoreIn : XSuperListStatus()

    /**
     * 列表往上加载成功
     */
    object AtFrontLoadMoreSuccess : XSuperListStatus()

    /**
     * 列表往上加载失败
     */
    object AtFrontLoadMoreError : XSuperListStatus()

    /**
     * 列表往上已全部加载完毕
     */
    object AtFrontEnd : XSuperListStatus()
}
