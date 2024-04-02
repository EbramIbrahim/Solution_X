package com.example.solutionxarch.core.data.repository.remote

import okhttp3.ResponseBody
import retrofit2.http.Body
import retrofit2.http.HeaderMap
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface ApiService {


    @POST("{endpoint}")
    @JvmSuppressWildcards
    suspend fun post(
        @Path("endpoint") endPoint: String, @HeaderMap headers: Map<String, Any>,
        @QueryMap queries: Map<String, Any>, @Body bodyRequest: Any
    ): ResponseBody
}










