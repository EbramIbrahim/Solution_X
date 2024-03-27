package com.example.solutionxarch.features.login.domain.usecase

import com.example.solutionxarch.core.common.SolutionXException
import com.example.solutionxarch.features.login.domain.repository.LoginRepository
import com.example.solutionxarch.features.login.domain.models.User
import com.example.solutionxarch.core.common.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginWithEmailUC(
    private val loginRepository: LoginRepository,
) {

}

//    operator fun invoke(scope: CoroutineScope, onResult: (Result<User, SolutionXException>) -> Unit) {
//        scope.launch(Dispatchers.Main) {
//            onResult.invoke(Result.Loading())
//            try {
//                withContext(Dispatchers.IO) {
//                    val user = loginRepository.loginUserWithEmail()
//                    onResult.invoke(Result.Success(user))
//                }
//                withContext(Dispatchers.Main) {
//                    onResult.invoke(Result.Loading(false))
//                }
//            } catch (e: Exception) {
//                withContext(Dispatchers.IO) {
//                    val failureResource = if (e is SolutionXException)
//                        e
//                    else
//                        SolutionXException.Unknown(message = "Unknown error in GetCurrenciesUC: $e")
//
//                    onResult.invoke(Result.Failure(failureResource))
//                }
//
//                withContext(Dispatchers.Main) {
//                    onResult.invoke(Result.Loading(false))
//                }
//            }
//        }
//    }
//}
// fun <T>CoroutineScope.execute(onResult: (Result<T, SolutionXException>, ) -> Unit) {
//    launch(Dispatchers.Main) {
//        onResult.invoke(Result.Loading(true))
//        try {
//            withContext(Dispatchers.IO) {
//                //Success State
////                onResult.invoke(Result.Success(T))
//            }
//            withContext(Dispatchers.Main) {
//                onResult.invoke(Result.Loading(false))
//
//
//            }
//        } catch (e: Exception) {
//            withContext(Dispatchers.IO) {
//                val failureResource = if (e is SolutionXException)
//                    e
//                else
//                    SolutionXException.Unknown(message = "Unknown error in GetCurrenciesUC: $e")
//
//                onResult.invoke(Result.Failure(failureResource))
//
//            }
//
//            withContext(Dispatchers.Main) {
//                onResult.invoke(Result.Loading(false))
//
//            }
//        }
//    }
//}
