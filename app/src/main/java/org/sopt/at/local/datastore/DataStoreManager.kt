package org.sopt.at.local.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataStoreManager @Inject constructor(
    private val context: Context
) {
    private val Context.dataStore by preferencesDataStore(DATA_STORE_NAME)

    suspend fun saveUserInfo(id: String, password: String) {
        context.dataStore.edit { preferences ->
            preferences[ID_KEY] = id
            preferences[PASSWORD_KEY] = password
        }
    }

    fun getUserName(): Flow<String?> {
        return context.dataStore.data.map { preferences ->
            preferences[ID_KEY]
        }
    }

    suspend fun clearUserInfo() {
        context.dataStore.edit { preferences ->
            preferences.remove(ID_KEY)
            preferences.remove(PASSWORD_KEY)
        }
    }

    suspend fun clearAllData() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    companion object {
        private const val DATA_STORE_NAME = "at_sopt"
        private val ID_KEY = stringPreferencesKey("id_key")
        private val PASSWORD_KEY = stringPreferencesKey("password_key")
    }
}
