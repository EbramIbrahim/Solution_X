package com.example.solutionxarch.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.solutionxarch.login.common.Result
import com.example.solutionxarch.login.common.SolutionXException
import com.example.solutionxarch.login.domain.usecase.LoginUseCase
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class LoginViewModel(
    private val userCase: LoginUseCase
): ViewModel() {



    fun getUser() {
        viewModelScope.launch {
            userCase.loginWithEmailUC().collectLatest {
                when (it) {
                    is Result.Error -> {
                        when (it.error) {
                            SolutionXException.NoInternet -> TODO()
                            SolutionXException.RequestTimeOut -> TODO()
                            SolutionXException.ServerError -> TODO()
                            SolutionXException.TooManyRequest -> TODO()
                        }
                    }

                    is Result.Success -> TODO()
                }
            }
        }
    }

}