package org.sopt.at.domain.usecase

import org.sopt.at.domain.repository.UserRepository
import org.sopt.at.remote.base.BaseResponse
import javax.inject.Inject

class RequestSignInUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend fun invoke(
        id: String,
        password: String,
    ): BaseResponse {
        return userRepository.requestSignIn(
            id = id,
            password = password
        )
    }
}