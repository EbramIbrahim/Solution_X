package com.example.solutionxarch.features.login.domain.usecase

import com.example.solutionxarch.core.common.SolutionXException
import com.example.solutionxarch.features.login.domain.repository.LoginRepository
import com.example.solutionxarch.features.login.domain.models.User
import kotlinx.coroutines.flow.Flow
import com.example.solutionxarch.core.common.Result
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class LoginWithPhoneUC(
    private val loginRepository: LoginRepository
) {

    operator fun invoke(
        countryCode: String,
        phoneNumber: String,
        password: String
    ): Flow<Result<User, SolutionXException>> = flow {


        try {
            val user = loginRepository.loginUserWithPhone(countryCode, phoneNumber, password)
            emit(Result.Success(user))
        } catch (e: Exception) {
            val failureResponse = if (e is SolutionXException)
                e
            else {
                SolutionXException.Unknown(message = "Unknown error in GetCurrenciesUC: $e")
            }
            emit(Result.Failure(failureResponse))
        }
    }
}