package com.xxxxls.status

/**
 * 状态类型
 * @author Max
 * @date 2019-12-14.
 */
sealed class XStatus {

    //默认
    internal object Default : XStatus()

    //正常
    object Content : XStatus()

    //错误
    object Error : XStatus()

    //空数据
    object Empty : XStatus()

    //加载中
    object Loading : XStatus()

    //无网络
    object NoNetwork : XStatus()
}