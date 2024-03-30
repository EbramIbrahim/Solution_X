package com.example.solutionxarch.features.login.domain.usecase

import com.example.solutionxarch.core.common.SolutionXException
import com.example.solutionxarch.features.login.domain.repository.LoginRepository
import com.example.solutionxarch.features.login.domain.models.User
import com.example.solutionxarch.core.common.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LoginWithPhoneUC @Inject constructor(
    private val loginRepository: LoginRepository
) {

    operator fun invoke(
        userLoginData: Map<String, String>
    ): Flow<Result<User>> = flow {
        emit(Result.Loading(true))

        try {
            val user = loginRepository.loginUserWithPhone(userLoginData)
            emit(Result.Success(user))
            emit(Result.Loading(false))
        } catch (e: Exception) {
            val failureResource = if (e is SolutionXException)
                e
            else
                SolutionXException.Unknown(message = "Unknown error in GetCurrenciesUC: $e")
            emit(Result.Failure(failureResource))
            emit(Result.Loading(false))

        }
    }
}