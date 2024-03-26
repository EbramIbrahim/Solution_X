package com.example.solutionxarch.features.login.domain.contracts

import com.example.solutionxarch.features.login.data.models.UserDto
import com.example.solutionxarch.features.login.data.models.meal_dto.MealsDto

interface ILoginRemoteDataSource {
    suspend fun loginUserWithPhone(
        countryCode: String,
        number: String,
        password: String
    ): UserDto

   suspend fun loginUserWithEmail(): UserDto
   suspend fun loginUserWithSocial(): UserDto
}