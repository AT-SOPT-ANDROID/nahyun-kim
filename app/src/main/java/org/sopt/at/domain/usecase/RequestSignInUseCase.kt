package org.sopt.at.domain.usecase

import org.sopt.at.domain.repository.AuthRepository
import org.sopt.at.remote.base.BaseResponse
import javax.inject.Inject

class RequestSignInUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend fun invoke(
        id: String,
        password: String,
    ): BaseResponse {
        return authRepository.requestSignIn(
            id = id,
            password = password
        )
    }
}