package com.xxxxls.status

/**
 * 状态类型
 * @author Max
 * @date 2019-12-14.
 */
sealed class XStatus {

    //正常
    object Content : XStatus()

    //错误
    object Error : XStatus()

    //空数据
    object Empty : XStatus()

    //加载中
    object Loading : XStatus()

    //无网络
    object NoNetWork : XStatus()

//    //正常
//    CONTENT,
//
//    //错误
//    ERROR,
//
//    //空数据
//    EMPTY,
//
//    //加载中
//    LOADING
}