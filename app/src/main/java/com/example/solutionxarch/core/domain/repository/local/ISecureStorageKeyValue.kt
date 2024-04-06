package com.example.solutionxarch.core.domain.repository.local

interface ISecureStorageKeyValue<Model> {

    suspend fun secureSave(model: Model)

    suspend fun  read(): Model?
}