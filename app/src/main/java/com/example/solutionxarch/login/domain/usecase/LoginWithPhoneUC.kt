package com.example.solutionxarch.login.domain.usecase

import com.example.solutionxarch.login.domain.repository.LoginRepository
import com.example.solutionxarch.login.domain.models.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LoginWithPhoneUC(
    private val loginRepository: LoginRepository
) {

    operator fun invoke(): Flow<User> = flow {
        try {
            emit(loginRepository.loginUserWithPhone())
        }catch (e: Exception){}
    }
}