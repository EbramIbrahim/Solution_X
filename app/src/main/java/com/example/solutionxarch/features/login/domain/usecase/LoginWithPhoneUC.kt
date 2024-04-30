package com.example.solutionxarch.features.login.domain.usecase

import com.example.solutionxarch.core.common.SolutionXException
import com.example.solutionxarch.features.login.domain.repository.LoginRepository
import com.example.solutionxarch.features.login.domain.models.User
import com.example.solutionxarch.core.common.Result
import com.example.solutionxarch.features.login.data.models.request.UserRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginWithPhoneUC @Inject constructor(
    private val loginRepository: LoginRepository
) {

    operator fun invoke(
        scope: CoroutineScope,
        onResult: (Result<User>) -> Unit,
        needLoading: Boolean = true,
        request: UserRequest
    ) {
        scope.launch(Dispatchers.Main) {
            if (needLoading) {
                onResult.invoke(Result.Loading(true))
            }
            withContext(Dispatchers.IO) {
                try {
                    val result = loginRepository.loginUserWithPhone(request)
                    onResult.invoke(Result.Success(result))
                    loginRepository.saveUser(result)
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        val failureResource = if (e is SolutionXException)
                            e
                        else
                            SolutionXException.Unknown(message = "Unknown error in LoginWithPhoneUC: $e")
                        onResult.invoke(Result.Failure(failureResource))
                    }
                }
            }
            withContext(Dispatchers.Main) {
                if (needLoading) {
                    onResult.invoke(Result.Loading(false))
                }
            }
        }
    }
}

