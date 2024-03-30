package com.example.solutionxarch.features.login.data.models.dto

import com.google.gson.annotations.SerializedName

data class Country(
    @SerializedName("code")
    val code: String?,
    @SerializedName("currency")
    val currency: String?,
    @SerializedName("flag")
    val flag: String?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("phone_code")
    val phoneCode: String?
)