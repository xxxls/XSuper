package com.xxxxls.xsuper.net.call

import com.xxxxls.xsuper.net.XSuperResult

/**
 * 协程请求
 * @author Max
 * @date 2019-12-01.
 */
class CoroutineCall<T> : XSuperCall<T> {

    override fun enqueue(callback: XSuperCall<XSuperResult<T>>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun execute(): XSuperResult<T> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun cancel() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isCancelled(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}