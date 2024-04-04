package com.example.solutionxarch.core.domain.repository.local

import com.example.solutionxarch.core.domain.repository.local.keys.IStorageKeys

interface IStorageKeyValue {

    suspend fun <DATA> save(key: IStorageKeys, model: DATA)

    suspend fun <DATA> read(key: IStorageKeys, model: DATA): DATA

    suspend fun <DATA> secureSave(model: DATA)

    suspend fun <DATA> secureRead(model: DATA)
}