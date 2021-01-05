package com.wj.sampleproject.entity

import kotlinx.serialization.Serializable

/**
 * 用户信息数据实体类
 *
 * @param admin 是否是管理员
 * @param chapterTops ？
 * @param collectIds ？
 * @param email 邮箱
 * @param icon 用户头像地址
 * @param id 用户 id
 * @param nickname 用户昵称
 * @param password 用户密码
 * @param publicName 公共名称
 * @param token 用户 token
 * @param type ？
 * @param username 用户名
 *
 * * 创建时间：2019/10/14
 *
 * @author 王杰
 */
@Serializable
data class UserInfoEntity(
        val admin: String? = "",
//        val chapterTops: ArrayList<Any>? = arrayListOf(),
//        val collectIds: ArrayList<Any>? = arrayListOf(),
        val email: String? = "",
        val icon: String? = "",
        val id: String? = "",
        val nickname: String? = "",
        val password: String? = "",
        val publicName: String? = "",
        val token: String? = "",
        val type: String? = "",
        val username: String? = ""
)

