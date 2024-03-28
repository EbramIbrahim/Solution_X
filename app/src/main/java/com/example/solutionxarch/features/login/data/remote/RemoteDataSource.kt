package com.example.solutionxarch.features.login.data.remote

import com.example.solutionxarch.features.login.data.models.remote.UserLoginDto
import com.example.solutionxarch.features.login.domain.contracts.IRemoteDataSource
import retrofit2.http.POST
import retrofit2.http.QueryMap

interface RemoteDataSource: IRemoteDataSource {

    @POST("api/login")
    override suspend fun loginUserWithPhone(
        @QueryMap queries: Map<String, String>
    ): UserLoginDto



}