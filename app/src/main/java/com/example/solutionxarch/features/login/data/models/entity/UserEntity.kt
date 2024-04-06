package com.example.solutionxarch.features.login.data.models.entity

import kotlinx.serialization.Serializable

@Serializable
data class UserEntity(
    val username: String? = null,
    val token: String? = null,
    val email: String? = null,
    val id: Int? = null
)




