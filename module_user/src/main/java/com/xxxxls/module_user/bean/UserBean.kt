package com.xxxxls.module_user.bean

import androidx.room.Entity


/**
 * 用户Bean
 * @author Max
 * @date 2019-11-28.
 */
@Entity
data class UserBean(
    val admin: Boolean,
    val chapterTops: List<Any>,
    val collectIds: List<Any>,
    val email: String,
    val icon: String,
    val id: Int,
    val nickname: String,
    val password: String,
    val publicName: String,
    val token: String,
    val type: Int,
    val username: String
)