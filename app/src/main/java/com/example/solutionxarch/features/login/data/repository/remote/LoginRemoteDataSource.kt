package com.example.solutionxarch.features.login.data.repository.remote

import com.example.solutionxarch.core.domain.repository.remote.IRemoteDataSourceProvider
import com.example.solutionxarch.features.login.data.models.dto.UserLoginDto
import com.example.solutionxarch.features.login.data.models.request.UserRequest
import com.example.solutionxarch.features.login.domain.repository.remote.ILoginRemoteDataSource
import javax.inject.Inject

internal class LoginRemoteDataSource @Inject constructor(
    private val provider: IRemoteDataSourceProvider
) : ILoginRemoteDataSource {

    override suspend fun loginUserWithPhone(userRequest: UserRequest): UserLoginDto {
        return provider.post(
            responseWrappedModel = UserLoginDto::class.java,
            endpoint = "login",
            headers = hashMapOf("Accept-Language" to "ar"),
            requestBody = userRequest
        )
    }

}





