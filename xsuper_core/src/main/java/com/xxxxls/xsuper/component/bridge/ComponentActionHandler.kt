package com.xxxxls.xsuper.component.bridge


/**
 * 组件命令处理者
 * @author Max
 * @date 2019-12-04.
 */
interface ComponentActionHandler {

    /**
     * 接受组件命令
     */
    fun onAction(action: ComponentAction)
}