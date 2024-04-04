package com.example.solutionxarch.core.domain.repository.local

import com.example.solutionxarch.core.domain.repository.local.keys.IStorageKeys
import com.example.solutionxarch.features.login.data.models.entity.UserEntity

interface IStorageKeyValue {

    suspend fun <DATA> save(key: IStorageKeys, model: DATA)

    suspend fun <DATA> read(key: IStorageKeys, model: DATA): DATA

    suspend fun secureSave(model: UserEntity)

    suspend fun read(): UserEntity
}