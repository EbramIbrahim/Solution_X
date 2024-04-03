package com.example.solutionxarch.features.login.data.repository.local

import android.util.Log
import androidx.datastore.core.Serializer
import com.example.solutionxarch.features.login.data.models.entity.UserEntity
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

class UserEntitySerializer(
    private val cryptoManager: CryptoManager
): Serializer<UserEntity> {

    override val defaultValue: UserEntity
        get() = UserEntity()

    override suspend fun readFrom(input: InputStream): UserEntity {
        val decryptedUser = cryptoManager.decrypt(input)
        Log.e("DecryptUser", decryptedUser.toString())
        return try {
         Json.decodeFromString(
             deserializer = UserEntity.serializer(),
             string = decryptedUser.decodeToString()
         )
        } catch (e: SerializationException) {
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(t: UserEntity, output: OutputStream) {
        cryptoManager.encrypt(
            bytes =  Json.encodeToString(
                serializer = UserEntity.serializer(),
                value = t
            ).encodeToByteArray(),
            output
        )
    }
}