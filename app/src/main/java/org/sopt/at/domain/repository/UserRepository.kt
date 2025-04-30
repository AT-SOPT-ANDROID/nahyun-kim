package org.sopt.at.domain.repository

import kotlinx.coroutines.flow.Flow
import org.sopt.at.domain.model.UserInfo

interface UserRepository {

    suspend fun saveUserInfo(userInfo: UserInfo)

    fun getUserName(): Flow<String?>

    suspend fun clearUserInfo()
}