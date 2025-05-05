package org.sopt.at.data.model

import org.sopt.at.domain.model.UserInfo

data class UserEntity(
    val id: String,
    val password: String
)

fun UserInfo.toEntity(): UserEntity =
    UserEntity(id, password)