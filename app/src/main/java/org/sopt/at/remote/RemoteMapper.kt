package org.sopt.at.remote

import org.sopt.at.domain.model.User
import org.sopt.at.remote.model.SignUpRequest

object RemoteMapper {
    fun User.toDTO(): SignUpRequest {
        return SignUpRequest(
            id = this.id,
            password = this.password,
            nickname = this.nickname
        )
    }
}