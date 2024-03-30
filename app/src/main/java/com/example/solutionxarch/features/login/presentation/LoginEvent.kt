package com.example.solutionxarch.features.login.presentation


sealed interface LoginEvent {

    data class UserLogin(val userLoginData: Map<String, String>): LoginEvent

}