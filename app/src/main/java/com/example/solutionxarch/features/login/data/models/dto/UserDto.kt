package com.example.solutionxarch.features.login.data.models.dto

import com.google.gson.annotations.SerializedName

data class UserDto(
    val country: Country?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("email_verified")
    val emailVerified: Boolean?,
    @SerializedName("firstname")
    val firstname: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("image")
    val image: Any?,
    @SerializedName("lastname")
    val lastname: String?,
    @SerializedName("middlename")
    val middleName: String?,
    val phone: Phone?,
    @SerializedName("phone_verified")
    val phoneVerified: Boolean?,
    @SerializedName("username")
    val username: String?
)