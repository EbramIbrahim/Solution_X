package com.example.solutionxarch.features.login.domain.contracts

import com.example.solutionxarch.features.login.data.models.remote.UserLoginDto

interface IRemoteDataSource {
    suspend fun loginUserWithPhone(
        params: Map<String, String>
    ): UserLoginDto


}