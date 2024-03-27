package com.example.solutionxarch.features.login.data.remote

import com.example.solutionxarch.features.login.data.models.UserDto
import com.example.solutionxarch.core.domain.contracts.IRemoteDataSource
import com.example.solutionxarch.features.login.data.models.UserLoginDto
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import retrofit2.http.Query

interface RemoteDataSource: IRemoteDataSource {

    @FormUrlEncoded
    @POST("api/login")
    override suspend fun loginUserWithPhone(
        @Field("country_code") countryCode: String,
        @Field("number") number: String,
        @Field("password") password: String
    ): UserLoginDto

    override suspend fun loginUserWithEmail(): UserDto {
        TODO("Not yet implemented")
    }

    override suspend fun loginUserWithSocial(): UserDto {
        TODO("Not yet implemented")
    }


}