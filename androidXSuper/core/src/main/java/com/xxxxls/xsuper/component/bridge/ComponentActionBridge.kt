package com.xxxxls.xsuper.component.bridge


/**
 * 组件命令 通信桥梁（发送组件命令）
 * @author Max
 * @date 2020-11-28.
 */
interface ComponentActionBridge {
    /**
     ** 发送组件命令
     */
    fun sendAction(action: ComponentAction)
}