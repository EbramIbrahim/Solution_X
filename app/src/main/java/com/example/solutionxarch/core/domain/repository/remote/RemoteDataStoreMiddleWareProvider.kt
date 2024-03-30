package com.example.solutionxarch.core.domain.repository.remote

import retrofit2.http.HeaderMap
import retrofit2.http.QueryMap
import retrofit2.http.Url

class RemoteDataStoreMiddleWareProvider() {

    @JvmOverloads
    fun <RESPONSE, PARAMS> get(
        @Url endpoint: String,
        @QueryMap queries: Map<String, PARAMS>? = null,
        @HeaderMap headers: Map<String, PARAMS>? = null,
    ): RESPONSE {
        TODO()
    }

    fun <RESPONSE, PARAMS> post(
        @Url endpoint: String,
        @QueryMap queries: Map<String, PARAMS>? = null,
        @HeaderMap headers: Map<String, PARAMS>? = null,
    ): RESPONSE {
        TODO()
    }

}












