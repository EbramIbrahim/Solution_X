package com.example.solutionxarch.features.login.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.solutionxarch.core.common.Result
import com.example.solutionxarch.features.login.data.models.UserLoginData
import com.example.solutionxarch.features.login.domain.usecase.LoginWithPhoneUC
import com.example.solutionxarch.features.login.domain.usecase.SaveTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginWithPhoneUC: LoginWithPhoneUC,
    private val saveTokenUseCase: SaveTokenUseCase
): ViewModel() {

    private val _loginState: MutableStateFlow<LoginState> = MutableStateFlow(LoginState())
    val loginState = _loginState.asStateFlow()

    fun onEvent(event: LoginEvent) {
        when(event) {
            is LoginEvent.UserLogin -> {
                loginWithEmail(event.userLoginData)
            }
        }
    }


    private fun loginWithEmail(userLoginData: UserLoginData) {
        viewModelScope.launch {
            loginWithPhoneUC(userLoginData).collect { result ->
                when(result) {
                    is Result.Failure -> {
                        _loginState.update { it.copy(isLoading = false, error = result.error.message ?: "") }
                        Log.e("state", result.error.toString())
                    }
                    is Result.Loading -> {
                        _loginState.update { it.copy(isLoading = true) }
                        Log.e("state", "loading")

                    }
                    is Result.Success -> {
                        Log.e("state", "succes")
                        _loginState.update { it.copy(user = result.data, message = "Login is done successfully") }
                        saveTokenUseCase(result.data.token)
                    }
                }
            }
        }
    }







}