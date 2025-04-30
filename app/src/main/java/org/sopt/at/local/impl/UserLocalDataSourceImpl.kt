package org.sopt.at.local.impl

import kotlinx.coroutines.flow.Flow
import org.sopt.at.data.local.UserLocalDataSource
import org.sopt.at.data.model.UserEntity
import org.sopt.at.local.datastore.DataStoreManager
import javax.inject.Inject

class UserLocalDataSourceImpl @Inject constructor(
    private val dataStoreManager: DataStoreManager
): UserLocalDataSource {

    override suspend fun saveUserInfo(user: UserEntity) {
        dataStoreManager.saveUserInfo(id = user.id, password = user.password)
    }

    override fun getUserName(): Flow<String?> =
        dataStoreManager.getUserName()

    override suspend fun clear() {
        dataStoreManager.clearAllData()
    }
}