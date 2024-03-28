package com.example.solutionxarch.features.login.data.models.remote

import com.google.gson.annotations.SerializedName

data class UserLoginDto(
    @SerializedName("message")
    val message: String?,
    @SerializedName("token")
    val token: String?,
    val userDto: UserDto?
)