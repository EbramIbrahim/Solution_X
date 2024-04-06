package com.example.solutionxarch.core.data.repository.local.cipher

import androidx.datastore.core.Serializer
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import java.io.InputStream
import java.io.OutputStream

class EntitySerializer<T>(
    private val cryptoManager: CryptoManager,
    private val serializer: KSerializer<T>,
    private val defaultValueProvider: () -> T
) : Serializer<T> {

    override val defaultValue: T
        get() = defaultValueProvider()

    override suspend fun readFrom(input: InputStream): T {
        val decryptedData = cryptoManager.decrypt(input)
        return try {
            Json.decodeFromString(serializer, decryptedData.decodeToString())
        } catch (e: SerializationException) {
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(t: T, output: OutputStream) {
        cryptoManager.encrypt(
            bytes = Json.encodeToString(serializer, t).encodeToByteArray(),
            output
        )
    }
}