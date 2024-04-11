package com.example.solutionxarch.core.data.repository.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.solutionxarch.R
import com.example.solutionxarch.core.common.SolutionXException
import com.example.solutionxarch.core.common.Utils
import com.example.solutionxarch.core.domain.repository.local.IStorageKeyValue
import com.example.solutionxarch.core.domain.repository.local.keys.IStorageKeys
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

class DataStoreStorageKeyValue(
    private val context: Context,
) : IStorageKeyValue {

    private val Context.dataStore: DataStore<Preferences> by
    preferencesDataStore(name = Utils.USER_PREFERENCES)



    override suspend fun <DATA> save(key: IStorageKeys, model: DATA) {
        context.dataStore.edit { settings ->
            when (model) {
                is String -> {
                    settings[stringPreferencesKey(key.key)] = model
                }

                is Int -> {
                    settings[intPreferencesKey(key.key)] = model
                }

                is Double -> {
                    settings[doublePreferencesKey(key.key)] = model
                }

                is Boolean -> {
                    settings[booleanPreferencesKey(key.key)] = model
                }

                is Long -> {
                    settings[longPreferencesKey(key.key)] = model
                }

                is Float -> {
                    settings[floatPreferencesKey(key.key)] = model
                }

            }
        }
    }

    @Suppress("UNCHECKED_CAST")
    override suspend fun <DATA> read(key: IStorageKeys, model: DATA): DATA {
        return when (model) {
            is String -> {
                (context.dataStore.data.map { it[stringPreferencesKey(key.key)] }.firstOrNull()
                    ?: model) as DATA
            }

            is Int -> {
                (context.dataStore.data.map { it[intPreferencesKey(key.key)] }.firstOrNull()
                    ?: model) as DATA
            }

            is Boolean -> {
                (context.dataStore.data.map { it[booleanPreferencesKey(key.key)] }.firstOrNull()
                    ?: model) as DATA
            }

            is Double -> {
                (context.dataStore.data.map { it[doublePreferencesKey(key.key)] }.firstOrNull()
                    ?: model) as DATA
            }

            is Long -> {
                (context.dataStore.data.map { it[longPreferencesKey(key.key)] }.firstOrNull()
                    ?: model) as DATA
            }

            is Float -> {
                (context.dataStore.data.map { it[floatPreferencesKey(key.key)] }.firstOrNull()
                    ?: model) as DATA
            }

            else -> {
                throw SolutionXException.Local.IOOperation(R.string.un_supported_type)
            }
        }
    }
}