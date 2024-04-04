package com.example.solutionxarch.features.login.data.repository.local


import com.example.solutionxarch.core.data.repository.local.keys.StorageKeys
import com.example.solutionxarch.core.domain.repository.local.IStorageKeyValue
import com.example.solutionxarch.features.login.data.models.entity.UserEntity
import com.example.solutionxarch.features.login.domain.repository.local.ILoginLocalDataSource
import javax.inject.Inject

class LoginLocalDataSource @Inject constructor(
    private val provider: IStorageKeyValue
) : ILoginLocalDataSource {


    override suspend fun saveUser(user: UserEntity) {
        provider.secureSave(user)
    }

    override suspend fun getUser(): UserEntity {
        return provider.read()
    }
}