package org.sopt.at.domain.usecase

import org.sopt.at.domain.repository.AuthRepository
import javax.inject.Inject

class GetUserIdUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    operator fun invoke() =
        authRepository.getUserId()
}