package com.xxxxls.example.bean

import com.xxxxls.adapter.multi.MultiItemEntity

/**
 * 测试数据
 * @author Max
 * @date 2019-12-07.
 */
data class TestPagingBean(
    val id: Int,
    val content: String,
    val title: String
) : MultiItemEntity {
    override fun getItemType(): Int {
        return id % 2
    }
}