package com.example.solutionxarch.features.login.presentation

import com.example.solutionxarch.core.data.models.UserLoginData

sealed interface LoginEvent {

    data class UserLogin(val userLoginData: UserLoginData): LoginEvent

}