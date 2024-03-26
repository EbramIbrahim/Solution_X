package com.example.solutionxarch.features.login.data.remote

import com.example.solutionxarch.features.login.data.models.UserDto
import com.example.solutionxarch.features.login.data.models.meal_dto.MealsDto
import com.example.solutionxarch.features.login.domain.contracts.ILoginRemoteDataSource
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface LoginRemoteDataSource: ILoginRemoteDataSource {

    @POST("api/login")
    override suspend fun loginUserWithPhone(
        @Query("country_code") countryCode: String,
        @Query("number") number: String,
        @Query("password") password: String
    ): UserDto

    override suspend fun loginUserWithEmail(): UserDto {
        TODO("Not yet implemented")
    }

    override suspend fun loginUserWithSocial(): UserDto {
        TODO("Not yet implemented")
    }


}