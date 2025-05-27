package org.sopt.at.domain.usecase

import org.sopt.at.domain.repository.UserRepository
import org.sopt.at.remote.base.BaseResponse
import javax.inject.Inject

class EditNicknameUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend fun invoke(
        nickname: String
    ): BaseResponse {
        return userRepository.editNickname(
            nickname = nickname
        )
    }
}