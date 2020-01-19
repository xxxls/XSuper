package com.xxxxls.logger.interceptor

import com.xxxxls.logger.LogEntity

/**
 * 拦截器
 * PS：按添加的顺序执行拦截器
 * @author Max
 * @date 2020-01-19.
 */
interface Interceptor {

    /**
     * 拦截
     * @param logEntity 日志实体
     * @return 如果返回null，则不会继续向下执行其他拦截器
     */
    fun onIntercept(logEntity: LogEntity): LogEntity?
}