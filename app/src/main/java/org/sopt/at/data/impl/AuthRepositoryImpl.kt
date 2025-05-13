package org.sopt.at.data.impl

import kotlinx.coroutines.flow.Flow
import org.sopt.at.data.local.UserLocalDataSource
import org.sopt.at.data.remote.AuthRemoteDataSource
import org.sopt.at.domain.model.User
import org.sopt.at.domain.repository.AuthRepository
import org.sopt.at.remote.base.BaseResponse
import org.sopt.at.remote.model.SignInResponse
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authRemoteDataSource: AuthRemoteDataSource,
    private val userLocalDataSource: UserLocalDataSource
): AuthRepository {

    override suspend fun requestSignUp(user: User): BaseResponse {
        return authRemoteDataSource.postSignUp(user)
    }

    override suspend fun requestSignIn(
        id: String,
        password: String
    ): SignInResponse {
        val response = authRemoteDataSource.postSignIn(id, password)
        if (response.success) {
            saveUserId(response.result.userId) // userId 저장
        }
        return response
    }

    private suspend fun saveUserId(userId: Long) {
        userLocalDataSource.saveUserId(userId)
    }

    override fun getUserId(): Flow<Long?> {
        return userLocalDataSource.getUserId()
    }

    override suspend fun clearUserInfo() {
        userLocalDataSource.clear()
    }
}