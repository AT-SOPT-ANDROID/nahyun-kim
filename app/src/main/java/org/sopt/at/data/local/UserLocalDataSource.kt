package org.sopt.at.data.local

import kotlinx.coroutines.flow.Flow

interface UserLocalDataSource {

    suspend fun saveUserId(userId: Long)

    fun getUserId(): Flow<Long?>

    suspend fun clear()
}