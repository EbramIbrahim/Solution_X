package com.example.solutionxarch.login.domain.usecase

import com.example.solutionxarch.login.domain.repository.LoginRepository
import com.example.solutionxarch.login.domain.models.User
import com.example.solutionxarch.login.common.Result
import com.example.solutionxarch.login.common.SolutionXException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class LoginWithSocialUC(
    private val loginRepository: LoginRepository
) {

    operator fun invoke(): Flow<Result<User, SolutionXException>> = flow {

        try {
            val user = loginRepository.loginUserWithSocial()
            emit(Result.Success(user))
        } catch (e: HttpException) {

            emit(Result.Error(SolutionXException.ServerError))

        } catch (e: IOException) {

            emit(Result.Error(SolutionXException.NoInternet))

        }
    }
}