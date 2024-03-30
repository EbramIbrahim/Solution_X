package com.example.solutionxarch.features.login.domain.repository.local

import com.example.solutionxarch.features.login.domain.models.User

interface ILoginLocalDataSource {

    suspend fun saveToken(
        token: String,
    )

    suspend fun saveUser(
        user: User
    )

    suspend fun getToken(): String?

    suspend fun getUser(): String?


}