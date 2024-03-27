package com.example.solutionxarch.core.domain.contracts

import com.example.solutionxarch.features.login.data.models.UserDto
import com.example.solutionxarch.features.login.data.models.UserLoginDto

interface IRemoteDataSource {
    suspend fun loginUserWithPhone(
        countryCode: String,
        number: String,
        password: String
    ): UserLoginDto

   suspend fun loginUserWithEmail(): UserDto
   suspend fun loginUserWithSocial(): UserDto
}