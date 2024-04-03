package com.example.solutionxarch.features.login.presentation

import com.example.solutionxarch.features.login.data.models.entity.UserEntity
import com.example.solutionxarch.features.login.domain.models.User

data class LoginState(
    val user: User? = null,
    val userEntity: UserEntity? = null,
    val message: String? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
