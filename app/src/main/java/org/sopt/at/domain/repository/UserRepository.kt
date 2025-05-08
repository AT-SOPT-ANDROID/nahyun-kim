package org.sopt.at.domain.repository

import kotlinx.coroutines.flow.Flow
import org.sopt.at.domain.model.User
import org.sopt.at.remote.base.BaseResponse
import org.sopt.at.remote.model.SignInResponse

interface UserRepository {

    suspend fun requestSignUp(userInfo: User): BaseResponse

    suspend fun requestSignIn(id: String, password: String): SignInResponse

    suspend fun saveUserInfo(user: User)

    fun getUserName(): Flow<String?>

    suspend fun clearUserInfo()
}