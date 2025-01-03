package com.example.solutionxarch.core.common

import androidx.datastore.preferences.core.stringPreferencesKey

object Utils {

    const val BASE_URL = "https://dev.api.altashirat.solutionplus.net/api/"
    const val USER_PREFERENCES = "user_preferences"
    const val KEY_REFERENCE = "SECRET_KEY"

    val userToken = stringPreferencesKey("user_token")

}