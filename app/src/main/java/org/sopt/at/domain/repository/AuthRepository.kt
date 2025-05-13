package org.sopt.at.domain.repository

import kotlinx.coroutines.flow.Flow
import org.sopt.at.domain.model.User
import org.sopt.at.remote.base.BaseResponse
import org.sopt.at.remote.model.SignInResponse

interface AuthRepository {

    suspend fun requestSignUp(userInfo: User): BaseResponse

    suspend fun requestSignIn(id: String, password: String): SignInResponse

    fun getUserId(): Flow<Long?>

    suspend fun clearUserInfo()
}