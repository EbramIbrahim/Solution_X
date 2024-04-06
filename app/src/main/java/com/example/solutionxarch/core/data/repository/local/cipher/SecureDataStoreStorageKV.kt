package com.example.solutionxarch.core.data.repository.local.cipher

import android.content.Context
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import com.example.solutionxarch.core.domain.repository.local.cipher.ISecureStorageKeyValue
import kotlinx.coroutines.flow.firstOrNull

class SecureDataStoreStorageKV<Model>(
    private val context: Context, serializer: Serializer<Model>
) : ISecureStorageKeyValue<Model> {

    // encrypted DataStore
    private val Context.cipherDataStore by dataStore(
        fileName = "user-entity.preferences_pb ",
        serializer = serializer
    )

    override suspend fun secureSave(model: Model) {
        context.cipherDataStore.updateData { model }
    }

    override suspend fun read(): Model? {
        return context.cipherDataStore.data.firstOrNull()
    }
}