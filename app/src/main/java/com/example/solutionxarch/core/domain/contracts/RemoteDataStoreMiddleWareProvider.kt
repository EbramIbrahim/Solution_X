package com.example.solutionxarch.core.domain.contracts

import retrofit2.http.HeaderMap
import retrofit2.http.QueryMap

class RemoteDataStoreMiddleWareProvider() {

    @JvmOverloads
    fun <RESPONSE, P> get(
        endpoint: String,
        @QueryMap queries: Map<String, P>? = null,
        @HeaderMap headers: Map<String, P>? = null,
    ): RESPONSE {
        TODO()
    }

    fun <RESPONSE, P> post(
        endpoint: String,
        headers: Map<String, P>? = null,
    ): RESPONSE {
        TODO()
    }

}












