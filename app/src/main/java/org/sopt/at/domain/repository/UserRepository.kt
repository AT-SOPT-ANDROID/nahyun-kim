package org.sopt.at.domain.repository

import org.sopt.at.remote.base.BaseResponse
import org.sopt.at.remote.model.MyNicknameResponse

interface UserRepository {

    suspend fun getMyNickname(): MyNicknameResponse

    suspend fun editNickname(nickname: String): BaseResponse

    suspend fun searchNickname(searchWord: String): List<String>
}