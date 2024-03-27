package com.example.solutionxarch.features.login.presentation

import com.example.solutionxarch.features.login.domain.models.User

data class LoginState(
    val user: User? = null,
    val message: String = "",
    val isLoading: Boolean = false,
    val error: String = ""
)
