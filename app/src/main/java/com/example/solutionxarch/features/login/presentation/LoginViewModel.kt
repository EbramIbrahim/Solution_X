package com.example.solutionxarch.features.login.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.solutionxarch.core.android.helpers.logger.writer.LogcatWriter
import com.example.solutionxarch.core.common.Result
import com.example.solutionxarch.features.login.data.models.request.UserRequest
import com.example.solutionxarch.features.login.domain.usecase.GetUserEntityUC
import com.example.solutionxarch.features.login.domain.usecase.LoginWithPhoneUC
import com.example.solutionxarch.features.login.domain.usecase.SaveUserEntityUC
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginWithPhoneUC: LoginWithPhoneUC,
    private val saveUserEntityUC: SaveUserEntityUC,
    private val getUserEntityUC: GetUserEntityUC
): ViewModel() {

    private val _loginState: MutableStateFlow<LoginState> = MutableStateFlow(LoginState())
    val loginState = _loginState.asStateFlow()



    fun onEvent(event: LoginEvent) {
        when(event) {
            is LoginEvent.UserLogin -> {
                loginWithEmail(event.userRequest)
            }

            LoginEvent.GetUserEntity -> getUserEntity()
        }
    }

    private fun getUserEntity() {
        viewModelScope.launch {
            val userEntity = getUserEntityUC()
            _loginState.update { it.copy(userEntity = userEntity) }
            LogcatWriter("Tag", true).debug(message = userEntity.toString(), clazz = String::class.java)

        }
    }


    private fun loginWithEmail(userRequest: UserRequest) {
        viewModelScope.launch {
            loginWithPhoneUC(userRequest).collect { result ->
                when(result) {
                    is Result.Failure -> {
                        _loginState.update { it.copy(isLoading = false, error = result.error.message ?: "") }
                        LogcatWriter("Tag", true).debug(message = result.error.toString(), clazz = String::class.java)
                    }
                    is Result.Loading -> {
                        _loginState.update { it.copy(isLoading = true) }

                    }
                    is Result.Success -> {
                        LogcatWriter("Tag", true).debug(message = result.data.toString(), clazz = String::class.java)
                        _loginState.update { it.copy(user = result.data, message = "Welcome ${result.data.username}") }
                        delay(1000L)
                        saveUserEntityUC(result.data)

                    }
                }
            }
        }
    }







}