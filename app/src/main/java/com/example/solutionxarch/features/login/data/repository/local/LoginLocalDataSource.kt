package com.example.solutionxarch.features.login.data.repository.local


import com.example.solutionxarch.core.data.repository.local.keys.StorageKeys
import com.example.solutionxarch.core.domain.repository.local.IStorageKeyValue
import com.example.solutionxarch.features.login.data.models.entity.UserEntity
import com.example.solutionxarch.features.login.domain.repository.local.ILoginLocalDataSource
import com.google.gson.Gson
import javax.inject.Inject

class LoginLocalDataSource @Inject constructor(
    private val provider: IStorageKeyValue
) : ILoginLocalDataSource {

    override suspend fun saveToken(token: String) {
        provider.save(StorageKeys.TOKEN_KEY, token)
    }

    override suspend fun saveUser(user: UserEntity) {
        provider.save(StorageKeys.USER_KEY, Gson().toJson(user))
    }

    override suspend fun getUser(): UserEntity? {
        val model = provider.read(StorageKeys.USER_KEY, "")
        return Gson().fromJson(model, UserEntity::class.java)
    }
}