package com.example.solutionxarch.features.login.domain.usecase

import com.example.solutionxarch.features.login.domain.repository.LoginRepository
import com.example.solutionxarch.features.login.domain.models.User
import com.example.solutionxarch.core.domain.usecase.BaseUseCase
import com.example.solutionxarch.features.login.data.models.request.UserRequest
import javax.inject.Inject

class LoginWithPhoneUC @Inject constructor(
    private val loginRepository: LoginRepository
): BaseUseCase<UserRequest, User>() {

    override suspend fun executeAndReturn(request: UserRequest): User {
        return loginRepository.loginUserWithPhone(request)
    }

}

