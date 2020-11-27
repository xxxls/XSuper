package com.xxxxls.xsuper.component.bridge


/**
 * 组件桥（通知组件一些事件或执行一些任务）
 * @author Max
 * @date 2019-12-04.
 */
interface IComponentBridge {

    /**
     * 组件命令
     */
    fun onAction(action: ComponentAction)
}