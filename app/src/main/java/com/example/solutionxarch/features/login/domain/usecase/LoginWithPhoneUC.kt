package com.example.solutionxarch.features.login.domain.usecase

import com.example.solutionxarch.core.common.SolutionXException
import com.example.solutionxarch.features.login.domain.repository.LoginRepository
import com.example.solutionxarch.features.login.domain.models.User
import com.example.solutionxarch.core.common.Result
import com.example.solutionxarch.features.login.data.models.UserLoginData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginWithPhoneUC(
    private val loginRepository: LoginRepository
) {

    operator fun invoke(
        scope: CoroutineScope,
        onResult: (Result<User, SolutionXException>) -> Unit,
        userLoginData: UserLoginData
    ) {
        scope.launch(Dispatchers.Main) {
            onResult.invoke(Result.Loading())
            try {
                withContext(Dispatchers.IO) {
                    val user = loginRepository.loginUserWithPhone(
                        userLoginData.countryCode,
                        userLoginData.number,
                        userLoginData.password
                    )
                    onResult.invoke(Result.Success(user))
                }
                withContext(Dispatchers.Main) {
                    onResult.invoke(Result.Loading(false))
                }
            } catch (e: Exception) {
                withContext(Dispatchers.IO) {
                    val failureResource = if (e is SolutionXException)
                        e
                    else
                        SolutionXException.Unknown(message = "Unknown error in GetCurrenciesUC: $e")

                    onResult.invoke(Result.Failure(failureResource))
                }

                withContext(Dispatchers.Main) {
                    onResult.invoke(Result.Loading(false))
                }
            }
        }
    }
}