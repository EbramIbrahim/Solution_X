package com.example.solutionxarch.features.login.data.repository.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.solutionxarch.core.common.EnumKeys
import com.example.solutionxarch.features.login.domain.repository.local.ILoginLocalDataSource
import com.example.solutionxarch.features.login.domain.models.User
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LoginLocalDataSource @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : ILoginLocalDataSource {

    override suspend fun saveToken(token: String) {
        dataStore.edit { settings ->
            settings[stringPreferencesKey(EnumKeys.TOKEN_KEY.key)] = token
        }
    }

    override suspend fun saveUser(user: User) {
        dataStore.edit { settings ->
            settings[stringPreferencesKey(EnumKeys.USER_KEY.key)] = user.username
        }
    }

    override suspend fun getToken(): String? {
        return dataStore.data.map { settings ->
            settings[stringPreferencesKey(EnumKeys.TOKEN_KEY.key)]
        }.firstOrNull()
    }

    override suspend fun getUser(): String? {
        return dataStore.data.map { settings ->
            settings[stringPreferencesKey(EnumKeys.USER_KEY.key)]
        }.firstOrNull()
    }
}