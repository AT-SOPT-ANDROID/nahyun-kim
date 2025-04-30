package org.sopt.at.domain.model

import org.sopt.at.data.model.UserEntity

data class UserInfo(
    val id: String = "",
    val password: String = ""
)

fun UserInfo.toEntity(): UserEntity =
    UserEntity(id, password)