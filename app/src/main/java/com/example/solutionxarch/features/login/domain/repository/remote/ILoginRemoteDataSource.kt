package com.example.solutionxarch.features.login.domain.repository.remote

import com.example.solutionxarch.features.login.data.models.dto.UserLoginDto

interface ILoginRemoteDataSource {
    suspend fun loginUserWithPhone(
        params: Map<String, String>
    ): UserLoginDto


}