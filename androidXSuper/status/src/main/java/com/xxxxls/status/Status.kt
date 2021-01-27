package com.xxxxls.status

/**
 * 状态类型
 * @author Max
 * @date 2019-12-14.
 */
sealed class Status {

    //默认
    internal object Default : Status()

    //正常
    object Content : Status()

    //错误
    object Error : Status()

    //空数据
    object Empty : Status()

    //加载中
    object Loading : Status()

    //无网络
    object NoNetwork : Status()
}