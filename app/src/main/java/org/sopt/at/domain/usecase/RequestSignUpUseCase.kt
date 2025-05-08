package org.sopt.at.domain.usecase

import org.sopt.at.domain.model.User
import org.sopt.at.domain.repository.UserRepository
import org.sopt.at.remote.base.BaseResponse
import javax.inject.Inject

class RequestSignUpUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend fun invoke(
        id: String,
        password: String,
        nickname: String
    ): BaseResponse {
        return userRepository.requestSignUp(
            User(
                id = id,
                password = password,
                nickname = nickname
            )
        )
    }
}