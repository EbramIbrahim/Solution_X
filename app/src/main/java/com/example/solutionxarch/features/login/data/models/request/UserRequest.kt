package com.example.solutionxarch.features.login.data.models.request

import com.google.gson.annotations.SerializedName

data class UserRequest(
    @SerializedName("phone")
    val phone: PhoneRequest,
    @SerializedName("password")
    val password: String
)

data class PhoneRequest(
    @SerializedName("country_code")
    val countryCode: String,
    @SerializedName("number")
    val number: String
)
