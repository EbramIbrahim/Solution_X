package com.example.solutionxarch.features.login.presentation

import com.example.solutionxarch.features.login.data.models.request.UserRequest


sealed interface LoginEvent {

    data class UserLogin(val userRequest: UserRequest): LoginEvent

}