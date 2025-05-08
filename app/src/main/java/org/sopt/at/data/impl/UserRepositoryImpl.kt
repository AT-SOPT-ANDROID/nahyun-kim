package org.sopt.at.data.impl

import kotlinx.coroutines.flow.Flow
import org.sopt.at.data.local.UserLocalDataSource
import org.sopt.at.data.model.toEntity
import org.sopt.at.data.remote.UserRemoteDataSource
import org.sopt.at.domain.model.User
import org.sopt.at.domain.repository.UserRepository
import org.sopt.at.remote.base.BaseResponse
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userRemoteDataSource: UserRemoteDataSource,
    private val userLocalDataSource: UserLocalDataSource
): UserRepository {

    override suspend fun requestSignUp(user: User): BaseResponse {
        return userRemoteDataSource.postSignUp(user)
    }

    override suspend fun saveUserInfo(user: User) {
        userLocalDataSource.saveUserInfo(user.toEntity())
    }

    override fun getUserName(): Flow<String?> {
        return userLocalDataSource.getUserName()
    }

    override suspend fun clearUserInfo() {
        userLocalDataSource.clear()
    }

}