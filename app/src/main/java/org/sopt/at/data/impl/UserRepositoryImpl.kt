package org.sopt.at.data.impl

import kotlinx.coroutines.flow.Flow
import org.sopt.at.data.local.UserLocalDataSource
import org.sopt.at.data.model.toEntity
import org.sopt.at.domain.model.UserInfo
import org.sopt.at.domain.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userLocalDataSource: UserLocalDataSource
): UserRepository {

    override suspend fun saveUserInfo(userInfo: UserInfo) {
        userLocalDataSource.saveUserInfo(userInfo.toEntity())
    }

    override fun getUserName(): Flow<String?> {
        return userLocalDataSource.getUserName()
    }

    override suspend fun clearUserInfo() {
        userLocalDataSource.clear()
    }

}