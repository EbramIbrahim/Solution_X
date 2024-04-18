package com.example.solutionxarch.features.login.data.repository.repo

import com.example.solutionxarch.features.login.data.models.dto.UserLoginDto
import com.example.solutionxarch.features.login.data.models.request.UserRequest
import com.example.solutionxarch.features.login.domain.repository.remote.ILoginRemoteDataSource

class FakeLoginRemoteDS(val userLoginDto: UserLoginDto) : ILoginRemoteDataSource {

    override suspend fun loginUserWithPhone(userRequest: UserRequest): UserLoginDto {
        return if (
            userRequest.phone.countryCode == "0020" &&
            userRequest.phone.number == "100100100" &&
            userRequest.password == "123456789"
        ) userLoginDto
        else UserLoginDto(null, null, null)
    }
}