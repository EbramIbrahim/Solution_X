package com.example.solutionxarch.features.login.domain.usecase

import com.example.solutionxarch.core.common.SolutionXException
import com.example.solutionxarch.features.login.domain.repository.LoginRepository
import com.example.solutionxarch.features.login.domain.models.User
import com.example.solutionxarch.core.common.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class LoginWithEmailUC(
    private val loginRepository: LoginRepository,
) {

    operator fun invoke(): Flow<Result<User, SolutionXException>> = flow {
        try {

            val user = loginRepository.loginUserWithEmail()
            emit(Result.Success(user))

        } catch (e: Exception) {}

    }
}