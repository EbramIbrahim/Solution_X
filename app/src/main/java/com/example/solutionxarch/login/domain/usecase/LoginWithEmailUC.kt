package com.example.solutionxarch.login.domain.usecase

import com.example.solutionxarch.core.common.SolutionXException
import com.example.solutionxarch.login.domain.repository.LoginRepository
import com.example.solutionxarch.login.domain.models.User
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

        } catch (e: HttpException) {

            emit(
                Result.Failure(
                    SolutionXException.ServerError(
                        e.message ?: ""
                    )
                )
            )

        } catch (e: IOException) {
            emit(
                Result.Failure(
                    SolutionXException.NoInternet(
                        e.message ?: ""
                    )
                )
            )
        } catch (e: StringIndexOutOfBoundsException) {
            emit(
                Result.Failure(
                    SolutionXException.RequestTimeOut(
                        e.message ?: ""
                    )
                )
            )
        } catch (e: Exception) {
            emit(
                Result.Failure(
                    SolutionXException.TooManyRequest(
                        e.message ?: ""
                    )
                )
            )
        }


    }
}