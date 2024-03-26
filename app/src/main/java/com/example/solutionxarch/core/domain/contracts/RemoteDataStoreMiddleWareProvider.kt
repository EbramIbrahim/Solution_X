package com.example.solutionxarch.core.domain.contracts

import okhttp3.*

class RemoteDataStoreMiddleWareProvider() {

    fun <T, P> get(
        endpoint: String,
        queries: Map<String, P>? = null,
        headers: Map<String, P>? = null,
    ): T {
        TODO()
    }

    fun <T, P> post(
        endpoint: String,
        headers: Map<String, P>? = null,
    ): T {
        TODO()
    }

}












