package com.example.solutionxarch.features.login.data.local

import com.example.solutionxarch.core.domain.contracts.LocalDataStoreMiddleWareProvider
import com.example.solutionxarch.core.domain.contracts.ILocalDataSource

class LoginLocalDataSource(
    private val localDataStoreMiddleWareProvider: LocalDataStoreMiddleWareProvider
): ILocalDataSource {

    override suspend fun <T> save(key: String, data: T) {
        TODO("Not yet implemented")
    }


}