package org.sopt.at.data.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import org.sopt.at.data.local.UserLocalDataSource
import org.sopt.at.data.remote.UserRemoteDataSource
import org.sopt.at.domain.model.User
import org.sopt.at.domain.repository.UserRepository
import org.sopt.at.remote.base.BaseResponse
import org.sopt.at.remote.model.MyNicknameResponse
import org.sopt.at.remote.model.SignInResponse
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource,
    private val userLocalDataSource: UserLocalDataSource
): UserRepository {

    override suspend fun requestSignUp(user: User): BaseResponse {
        return userRemoteDataSource.postSignUp(user)
    }

    override suspend fun requestSignIn(
        id: String,
        password: String
    ): SignInResponse {
        val response = userRemoteDataSource.postSignIn(id, password)
        if (response.success) {
            userLocalDataSource.saveUserId(response.result.userId)
        }
        return response
    }

    override suspend fun getMyNickname(): MyNicknameResponse {
        return userRemoteDataSource.getMyNickname(getUserId().first() ?: -1)
    }

    override suspend fun saveUserId(userId: Long) {
        userLocalDataSource.saveUserId(userId)
    }

    override fun getUserId(): Flow<Long?> {
        return userLocalDataSource.getUserId()
    }

    override suspend fun clearUserInfo() {
        userLocalDataSource.clear()
    }

}