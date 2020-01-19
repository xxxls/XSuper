package com.xxxxls.logger.interceptor

import com.xxxxls.logger.LogEntity

/**
 * 黑名单-标签拦截器
 * @author Max
 * @date 2020-01-19.
 */
class BlacklistTagInterceptor : LogInterceptor {

    /**
     * 黑名单列表-标签
     */
    private var blacklistTags: List<String>? = null

    constructor(vararg blacklistTags: String) {
        this.blacklistTags = blacklistTags.toList()
    }

    constructor(blacklistTags: List<String>) {
        this.blacklistTags = blacklistTags
    }

    override fun onIntercept(logEntity: LogEntity): LogEntity? {
        this.blacklistTags?.forEach {
            if (logEntity.tag == it) {
                return null
            }
        }
        return logEntity
    }
}