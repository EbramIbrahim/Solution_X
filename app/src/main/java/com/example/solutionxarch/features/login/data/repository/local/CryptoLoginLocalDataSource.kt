package com.example.solutionxarch.features.login.data.repository.local

import android.content.Context
import androidx.datastore.dataStore
import com.example.solutionxarch.features.login.data.models.entity.UserEntity
import kotlinx.coroutines.flow.firstOrNull

class CryptoLoginLocalDataSource(
    private val context: Context
) {

    private val Context.dataStore by dataStore(
        fileName = "user-entity.json",
        serializer = UserEntitySerializer(CryptoManager())
    )


    suspend fun saveUserEntity(userEntity: UserEntity) {
        context.dataStore.updateData {
            userEntity
        }
    }

    suspend fun getUserEntity(): UserEntity {
        return context.dataStore.data.firstOrNull() ?: UserEntity()
    }


}





