package org.sopt.at.local.impl

import kotlinx.coroutines.flow.Flow
import org.sopt.at.data.local.UserLocalDataSource
import org.sopt.at.local.datastore.DataStoreManager
import javax.inject.Inject

class UserLocalDataSourceImpl @Inject constructor(
    private val dataStoreManager: DataStoreManager
): UserLocalDataSource {

    override suspend fun saveUserId(userId: Long) {
        dataStoreManager.saveUserId(id = userId)
    }

    override fun getUserId(): Flow<Long?> =
        dataStoreManager.getUserId()

    override suspend fun clear() {
        dataStoreManager.clearAllData()
    }
}