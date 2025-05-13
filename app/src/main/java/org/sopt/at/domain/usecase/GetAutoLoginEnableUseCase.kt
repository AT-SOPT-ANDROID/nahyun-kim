package org.sopt.at.domain.usecase

import org.sopt.at.domain.repository.AuthRepository
import javax.inject.Inject

class GetAutoLoginEnableUseCase @Inject constructor(
    private val authRepository: AuthRepository
) {

    suspend operator fun invoke() =
        authRepository.isSignInUser()
}