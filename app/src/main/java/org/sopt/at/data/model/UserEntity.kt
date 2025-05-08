package org.sopt.at.data.model

import org.sopt.at.domain.model.User

data class UserEntity(
    val id: String,
    val password: String
)

fun User.toEntity(): UserEntity =
    UserEntity(id, password)