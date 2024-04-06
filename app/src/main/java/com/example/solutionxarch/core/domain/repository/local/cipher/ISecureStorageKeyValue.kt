package com.example.solutionxarch.core.domain.repository.local.cipher

interface ISecureStorageKeyValue<Model> {

    suspend fun secureSave(model: Model)

    suspend fun  read(): Model?
}