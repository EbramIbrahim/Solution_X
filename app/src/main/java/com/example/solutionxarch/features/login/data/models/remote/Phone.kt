package com.example.solutionxarch.features.login.data.models.remote

import com.google.gson.annotations.SerializedName

data class Phone(
    @SerializedName("country_code")
    val countryCode: String?,
    @SerializedName("extension")
    val extension: Any?,
    @SerializedName("holder_name")
    val holder_name: Any?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("number")
    val number: String?,
    @SerializedName("type")
    val type: Any?
)