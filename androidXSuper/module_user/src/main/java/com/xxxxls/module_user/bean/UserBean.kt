package com.xxxxls.module_user.bean

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey


/**
 * 用户信息
 * @author Max
 * @date 2019-11-28.
 */
@Entity(tableName = "tb_user")
class UserBean {
    var admin: Boolean? = null

    @Ignore
    val chapterTops: List<Any>? = null

    @Ignore
    val collectIds: List<Any>? = null

    var email: String? = null

    var icon: String? = null

    @PrimaryKey
    var id: Int? = null

    var nickname: String? = null

    var password: String? = null

    var publicName: String? = null

    var token: String? = null

    var type: Int? = null

    var username: String? = null
}