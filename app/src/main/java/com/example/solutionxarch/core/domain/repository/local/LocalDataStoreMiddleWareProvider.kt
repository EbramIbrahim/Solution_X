package com.example.solutionxarch.core.domain.repository.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

class LocalDataStoreMiddleWareProvider(
     val dataStore: DataStore<Preferences>,
) {

    suspend inline fun <reified T> saveData(key: String, data: T) {
        val serializedData = when (T::class) {
            String::class -> data as String
            Int::class -> data.toString()
            Boolean::class -> data.toString()
            else -> throw IllegalArgumentException("Unsupported data type: ${T::class}")
        }
        dataStore.edit { settings ->
            settings[stringPreferencesKey(key)] = serializedData
        }
    }

    private suspend fun fetchDataFromDataStore(key: String): String? {
        return dataStore.data.map { settings ->
            settings[stringPreferencesKey(key)]
        }.firstOrNull()
    }

}