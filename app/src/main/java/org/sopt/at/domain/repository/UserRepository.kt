package org.sopt.at.domain.repository

import kotlinx.coroutines.flow.Flow
import org.sopt.at.domain.model.User
import org.sopt.at.remote.base.BaseResponse
import org.sopt.at.remote.model.MyNicknameResponse
import org.sopt.at.remote.model.SignInResponse

interface UserRepository {

    suspend fun requestSignUp(userInfo: User): BaseResponse

    suspend fun requestSignIn(id: String, password: String): SignInResponse

    suspend fun getMyNickname(): MyNicknameResponse

    suspend fun searchNickname(searchWord: String): List<String>

    suspend fun editNickname(nickname: String): BaseResponse

    suspend fun saveUserId(userId: Long)

    fun getUserId(): Flow<Long?>

    suspend fun clearUserInfo()
}