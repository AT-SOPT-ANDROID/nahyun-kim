package org.sopt.at.domain.usecase

import org.sopt.at.domain.model.User
import org.sopt.at.domain.repository.AuthRepository
import org.sopt.at.remote.base.BaseResponse
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend fun invoke(
        id: String,
        password: String,
        nickname: String
    ): BaseResponse {
        return authRepository.requestSignUp(
            User(
                id = id,
                password = password,
                nickname = nickname
            )
        )
    }
}