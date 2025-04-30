package org.sopt.at.data.local

import kotlinx.coroutines.flow.Flow
import org.sopt.at.data.model.UserEntity

interface UserLocalDataSource {

    suspend fun saveUserInfo(user: UserEntity)

    fun getUserName(): Flow<String?>

    suspend fun clear()
}