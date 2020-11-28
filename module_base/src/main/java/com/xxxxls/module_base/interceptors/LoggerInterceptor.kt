package com.xxxxls.module_base.interceptors


import okhttp3.*
import okio.Buffer

import java.io.IOException
import java.nio.charset.Charset

/**
 * okhttp 日志拦截器
 * @author Max
 * @date 2019-12-06.
 */
class LoggerInterceptor(private val TAG: String = "LoggerInterceptor") : Interceptor {

    @Synchronized
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val startTime = System.nanoTime()
        com.xxxxls.utils.L.d(TAG, "----------Start----------")
        com.xxxxls.utils.L.d(TAG, String.format("请求地址：| %s", request.url))
        //执行请求
        val response = chain.proceed(request)
        printParams(request.body)
        //拿到请求结果
        val body = response.body
        val source = body!!.source()
        // Buffer the entire body.
        source.request(Integer.MAX_VALUE.toLong())
        val buffer = source.buffer()
        //接口数据大于16K不显示
        if (buffer.size > 1024 * 16) {
            com.xxxxls.utils.L.d(
                TAG, "请求返回：| (长度:" + buffer.size
                        + ")大于16K不打印,点击链接在网页查看 " + request.url.toString()
            )
            return response
        } else {
            var charset: Charset? = Charset.defaultCharset()
            val contentType = body.contentType()
            if (contentType != null) {
                charset = contentType.charset(charset)
            }
            val bodyString = buffer.clone().readString(charset!!)
            //String str = URLDecoder.decode(bodyString, "UTF-8");
            com.xxxxls.utils.L.d(TAG, String.format("请求返回：| %s", bodyString))
        }
        val endTime = System.nanoTime()
        val duration = (endTime - startTime) / 1e6
        com.xxxxls.utils.L.d(TAG, "----------End:请求耗时:" + duration + "毫秒----------")
        return response
    }

    @Throws(IOException::class)
    private fun printParams(body: RequestBody?) {
        if (body == null) {
            return
        }
        val buffer = Buffer()
        body.writeTo(buffer)
        var charset: Charset? = Charset.forName("UTF-8")
        val contentType = body.contentType()
        if (contentType != null) {
            charset = contentType.charset(charset)
        }
        val params = buffer.readString(charset!!)
        com.xxxxls.utils.L.d(TAG, "请求参数：| $params")
    }
}
