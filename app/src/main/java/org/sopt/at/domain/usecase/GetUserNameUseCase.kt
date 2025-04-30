package org.sopt.at.domain.usecase

import org.sopt.at.domain.repository.UserRepository
import javax.inject.Inject

class GetUserNameUseCase @Inject constructor(
    private val userRepository: UserRepository
) {

    operator fun invoke() =
        userRepository.getUserName()
}