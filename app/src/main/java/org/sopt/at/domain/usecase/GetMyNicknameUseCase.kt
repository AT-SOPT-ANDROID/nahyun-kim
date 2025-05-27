package org.sopt.at.domain.usecase

import org.sopt.at.domain.repository.UserRepository
import javax.inject.Inject

class GetMyNicknameUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    suspend operator fun invoke() =
        userRepository.getMyNickname()
}