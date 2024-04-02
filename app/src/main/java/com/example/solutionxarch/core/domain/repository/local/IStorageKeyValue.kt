package com.example.solutionxarch.core.domain.repository.local

import com.example.solutionxarch.core.domain.repository.local.keys.IStorageKeys

interface IStorageKeyValue {

    suspend fun <DATA> save(key: IStorageKeys, model: DATA)

    suspend fun <DATA> read(key: IStorageKeys, model: DATA): DATA
}