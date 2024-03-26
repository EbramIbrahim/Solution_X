package com.example.solutionxarch.features.login.domain.repository

import com.example.solutionxarch.features.login.domain.models.User

interface LoginRepository {
    suspend fun loginUserWithPhone(
        countryCode: String,
        phoneNumber: String,
        password: String
    ): User
    suspend fun loginUserWithEmail(): User
    suspend fun loginUserWithSocial(): User


    suspend fun saveToken(token: String)

    suspend fun saveUser(user: User)



}









