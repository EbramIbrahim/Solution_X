package com.example.solutionxarch.core.domain.contracts

internal interface ILocalDataSource {

    suspend fun <T> save(
        key: String,
        data: T,
    )


}