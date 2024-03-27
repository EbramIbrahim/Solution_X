package com.example.solutionxarch.features.login.data.models

data class UserLoginDto(
    val message: String,
    val token: String,
    val user: User
)