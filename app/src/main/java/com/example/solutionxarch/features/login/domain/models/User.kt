package com.example.solutionxarch.features.login.domain.models

data class User(
    val username: String,
    val token: String,
    val email: String,
    val id: Int
)
