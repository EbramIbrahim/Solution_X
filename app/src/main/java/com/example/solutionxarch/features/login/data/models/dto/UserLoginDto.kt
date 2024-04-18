package com.example.solutionxarch.features.login.data.models.dto

import com.google.gson.annotations.SerializedName

data class UserLoginDto(
    @SerializedName("message")
    val message: String? = null,
    @SerializedName("token")
    val token: String? = null,
    @SerializedName("user")
    val userDto: UserDto? = null
)