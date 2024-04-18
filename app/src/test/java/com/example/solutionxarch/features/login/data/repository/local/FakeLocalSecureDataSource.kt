package com.example.solutionxarch.features.login.data.repository.local

import android.content.Context
import androidx.datastore.dataStore
import com.example.solutionxarch.core.data.repository.local.cipher.CryptoManager
import com.example.solutionxarch.core.data.repository.local.cipher.EntitySerializer
import com.example.solutionxarch.core.domain.repository.local.cipher.ISecureStorageKeyValue
import com.example.solutionxarch.features.login.data.models.entity.UserEntity
import kotlinx.coroutines.flow.firstOrNull

class FakeLocalSecureDataSource(private val context: Context) : ISecureStorageKeyValue<UserEntity> {

    // encrypted DataStore
    private val Context.cipherDataStore by dataStore(
        fileName = "test.preferences_pb",
        serializer = EntitySerializer(CryptoManager(), UserEntity.serializer()) { UserEntity() }
    )

    override suspend fun secureSave(model: UserEntity) {
        context.cipherDataStore.updateData {
            model
        }
    }

    override suspend fun read(): UserEntity? {
        return context.cipherDataStore.data.firstOrNull()
    }
}