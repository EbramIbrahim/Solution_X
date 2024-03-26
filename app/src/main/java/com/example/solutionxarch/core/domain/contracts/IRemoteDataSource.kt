package com.example.solutionxarch.core.domain.contracts

import com.example.solutionxarch.features.login.data.models.UserDto

interface IRemoteDataSource {
    suspend fun loginUserWithPhone(
        countryCode: String,
        number: String,
        password: String
    ): UserDto

   suspend fun loginUserWithEmail(): UserDto
   suspend fun loginUserWithSocial(): UserDto
}