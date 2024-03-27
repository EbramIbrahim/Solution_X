package com.example.solutionxarch.features.login.data.models

data class User(
    val all_permissions: List<Any>,
    val birthdate: Any,
    val blocked: Int,
    val country: Country,
    val email: String,
    val email_verified: Boolean,
    val firstname: String,
    val id: Int,
    val image: Any,
    val lastname: String,
    val middlename: String,
    val phone: Phone,
    val phone_verified: Boolean,
    val username: String
)