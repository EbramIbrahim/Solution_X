package com.example.solutionxarch.features.login.domain.contracts

import com.example.solutionxarch.features.login.domain.models.User

interface ILocalDataSource {

    suspend fun saveToken(
        token: String,
    )

    suspend fun saveUser(
        user: User
    )

    suspend fun getToken(): String?

    suspend fun getUser(): String?


}