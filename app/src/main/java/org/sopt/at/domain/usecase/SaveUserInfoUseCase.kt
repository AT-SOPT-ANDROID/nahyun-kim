package org.sopt.at.domain.usecase

import org.sopt.at.domain.model.User
import org.sopt.at.domain.repository.UserRepository
import javax.inject.Inject

class SaveUserInfoUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(id: String, password: String) {
        userRepository.saveUserInfo(User(id, password))
    }
}
