package com.example.solutionxarch.login.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.solutionxarch.core.common.Result
import com.example.solutionxarch.core.common.SolutionXException

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
                            is SolutionXException.NoInternet -> {
                                Log.e("error", it.error.message.toString())
                            }
                            is SolutionXException.RequestTimeOut -> {
                                Log.e("error", it.error.message.toString())

                            }
                            is SolutionXException.ServerError -> {
                                Log.e("error", it.error.message.toString())

                            }
                            is SolutionXException.TooManyRequest -> {
                                Log.e("error", it.error.message.toString())
                            }
                        }
                    }

                    is Result.Success -> {
                        Log.e("success_login", "Login Successfully")
                    }
                }
            }
        }
    }

}