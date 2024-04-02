package com.example.solutionxarch.features.login.domain.repository.remote

import com.example.solutionxarch.features.login.data.models.dto.UserLoginDto
import com.example.solutionxarch.features.login.data.models.request.UserRequest

interface ILoginRemoteDataSource {
    suspend fun loginUserWithPhone(userRequest: UserRequest): UserLoginDto


}