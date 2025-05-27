package org.sopt.at.domain.usecase

import org.sopt.at.domain.repository.UserRepository
import javax.inject.Inject

class SearchUserNicknameUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend fun invoke(
        searchWord: String
    ): List<String> {
        return userRepository.searchNickname(searchWord)
    }
}