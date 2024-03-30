package com.example.solutionxarch.features.login.data.repository.remote

import com.example.solutionxarch.features.login.data.models.dto.UserLoginDto
import com.example.solutionxarch.features.login.domain.repository.remote.ILoginRemoteDataSource
import retrofit2.http.POST
import retrofit2.http.QueryMap

interface LoginRemoteDataSource: ILoginRemoteDataSource {

    @POST("api/login")
    override suspend fun loginUserWithPhone(
        @QueryMap params: Map<String, String>
    ): UserLoginDto



}